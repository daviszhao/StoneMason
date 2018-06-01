package io.github.daviszhao.stonemason.busEvent.services.old;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.daviszhao.stonemason.busEvent.constants.AskEventStatus;
import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.busEvent.event.AskEvent;
import io.github.daviszhao.stonemason.busEvent.payloads.FailureInfo;
import io.github.daviszhao.stonemason.busEvent.payloads.FailureReason;
import io.github.daviszhao.stonemason.db.event.tables.daos.AskRequstEventPublishDao;
import io.github.daviszhao.stonemason.db.event.tables.daos.EventWatchDao;
import io.github.daviszhao.stonemason.db.event.tables.daos.EventWatchProcessDao;
import io.github.daviszhao.stonemason.exceptions.event.EventException;
import io.github.daviszhao.stonemason.models.event.AskRequstEventPublish;
import io.github.daviszhao.stonemason.models.event.EventWatch;
import io.github.daviszhao.stonemason.models.event.EventWatchProcess;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Slf4j
public class EventWatchService {

    public static final int MAX_MESSAGE_COUNTS = 10000;
    @Inject
    private EventWatchDao watchDao;
    @Inject
    private EventWatchProcessDao eventWatchProcessRepository;
    @Inject
    private AskRequstEventPublishDao askRequestEventPublishRepository;
    @Inject
    private
    EventPublishService eventPublishService;
    @Inject
    private
    EventRegistry eventRegistry;
    @Inject
    private EventBus eventBus;
    @Inject
    private ObjectMapper objectMapper;
    private BlockingQueue<EventWatchProcess> queue = new LinkedBlockingQueue<>(MAX_MESSAGE_COUNTS);

    private AtomicBoolean firstTime = new AtomicBoolean(true);

    @Transactional@SneakyThrows(JsonProcessingException.class)
    public EventWatch watchAskEvents(AskParameter askParameter)  {

        EventWatch eventWatch = new EventWatch();
        eventWatch.setAskeventstatus(AskEventStatus.PENDING);
        eventWatch.setAskEventIdList(askParameter.getAskEvents().stream()
                .map(AskEvent::getId).collect(Collectors.toList()));
        if (askParameter.getCallbackClass() != null) {
            eventWatch.setCallbackclass(askParameter.getCallbackClass().getName());
        }
        if (!askParameter.getExtraParams().isEmpty()) {
            String json = null;
                json = objectMapper.writeValueAsString(askParameter.getExtraParams());
            eventWatch.setExtraparams(json);
        }
        if (askParameter.getTimeoutTime().isPresent()) {
            eventWatch.setTimeouttime(askParameter.getTimeoutTime().get());
        }
        eventWatch.setUnited(askParameter.isUnited());

        watchDao.insert(eventWatch);

        return eventWatch;
    }

    @Transactional
    public Optional<EventWatchProcess> processEventWatch(int watchId, AskEventStatus triggerStatus, FailureInfo failureInfo) {

        EventWatch eventWatch = watchDao.fetchOneById(watchId);
        if (eventWatch == null) {
            throw new EventException("根据ID没有找到EventWatch, watchId: " + watchId);
        }
        if (!eventWatch.getAskeventstatus().equals(AskEventStatus.PENDING)) {
            return Optional.empty();
        }

        if (!eventWatch.isUnited()) {

            String callbackClassName = eventWatch.getCallbackclass();
            String extraParams = eventWatch.getExtraparams();
            List<String> askEventIds = eventWatch.getAskEventIdList();
            List<AskRequstEventPublish> askEvents = eventPublishService.findAskRequestEventByEventId(askEventIds);

            if (askEventIds.size() != 1) {
                throw new EventException("EventWatch united为true, 但是askEventIds的size不为1, watchId: " + watchId);
            }
            eventWatch.setAskeventstatus(triggerStatus);
            executeCallback(triggerStatus.equals(AskEventStatus.SUCCESS), callbackClassName, extraParams,
                    askEvents, failureInfo);
            watchDao.update(eventWatch);

        } else {

            //对于united为true的eventWatch, 创建EventWatchProcess, 并加到队列, 异步进行处理
            EventWatchProcess eventWatchProcess = new EventWatchProcess();
            eventWatchProcess.setWatchid(watchId);
            eventWatchProcess.setStatus(ProcessStatus.NEW);
            if (failureInfo != null) {
                eventWatchProcess.setFailureinfo(failureInfo);
            }
            eventWatchProcessRepository.insert(eventWatchProcess);
            //这里不能加入处理队列, 因为之前的askRequestEventPublish.setAskEventStatus(askEventStatus), 到这里事务还没提交
            //如果此时队列里的数据就被拿出来处理的话, 就有问题了. 所以将eventWatchProcess作为返回值返回, 在事务完成后加入队列
            //addToQueue(EVENT_WATCH_PROCESS);
            return Optional.of(eventWatchProcess);
        }

        return Optional.empty();
    }

    @Transactional
    public void processUnitedEventWatch(EventWatchProcess eventWatchProcess) {


        /**
         *
         如果不为PENDING, 不做处理.
         如果为PENDING, 则根据AskResponseEvent的success是true还是false, 设置成SUCESS或FAILED. 然后根据watchId, 找到UnitedEventWatch.
         首先判断UnitedEventWatch的askEventStatus状态, 如果不为PENDING, 不做处理.
         如果为PENDING, 查询UnitedEventWatch的askEventIds列表, 根据这些askEvents的状态重新改变UnitedEventWatch的状态.
         改变逻辑:
         根据更新时间升序排列askEvents. 查询到第一个不为PENDING也不为SUCCESS状态的askEvent, 根据这个状态设置UnionEventWatch的状态, 并且触发失败逻辑.
         如果所有askEvents都为Success, 触发成功逻辑. 如果全为PENDING, 报错.
         成功逻辑: UnitedEventWatch状态设置为SUCCESS, 调用注册的回调函数SuccessCallback.
         失败逻辑: UnitedEventWatch状态设置为TIMEOUT/FAILED/CANCELLED. 调用注册的回调函数FailureCallback. 再次查询UnionEventWatch下所有的askEvents,判断他们的状态.
         如果为TIMEOUT/FAILED/CANCELLED, 不做处理.
         如果为PENDING/SUCCESS, 设置状态为TIMEOUT/FAILED/CANCELLED, 然后判断该askEvent是否实现了Revokable接口, 如果实现了, 需要发送RevokeAskEvent事件进行撤销操作.
         */
        log.debug(String.format("process event watch process, id: %d",
                eventWatchProcess.getId()));

        int watchId = eventWatchProcess.getWatchid();
        EventWatch eventWatch = watchDao.fetchOneById(watchId);
        if (eventWatch == null) {
            return;
        }
        if (AskEventStatus.PENDING != eventWatch.getAskeventstatus()) {
            return;
        }
        if (!eventWatch.isUnited()) {
            throw new EventException("EventWatch united为false, watchId: " + watchId);
        }

        FailureInfo failureInfo = (eventWatchProcess.getFailureinfo());

        String callbackClassName = eventWatch.getCallbackclass();
        String extraParams = eventWatch.getExtraparams();
        List<String> askEventIds = eventWatch.getAskEventIdList();
        List<AskRequstEventPublish> askEvents = eventPublishService.findAskRequestEventByEventId(askEventIds);

        AskEventStatus failedStatus = null;
        FailureInfo unitedFailedInfo = null;

        if (askEvents.stream().allMatch(ep -> AskEventStatus.SUCCESS == ep.getAskeventstatus())) {
            //所有askEvents都为Success, 触发成功逻辑
            eventWatch.setAskeventstatus(AskEventStatus.SUCCESS);
            executeCallback(true, callbackClassName, extraParams,
                    askEvents, failureInfo);
            watchDao.insert(eventWatch);

        } else if (askEvents.stream().allMatch(ep -> AskEventStatus.PENDING == ep.getAskeventstatus())) {

            if (eventWatch.getTimeouttime() != null && eventWatch.getTimeouttime().isBefore(LocalDateTime.now())) {
                failedStatus = AskEventStatus.TIMEOUT;
                unitedFailedInfo = new FailureInfo(FailureReason.TIMEOUT, LocalDateTime.now());
            } else {
                //所有askEvents都为PENDING, 报错
                throw new EventException(String.format("处理united watch事件的时候发现askEvent对应的状态都为PENDING, " +
                        "程序有bug? watchId: %d, askEventIds: %s", watchId, askEventIds.toString()));
            }

        } else {
            Optional<AskRequstEventPublish> failedEventPublish = askEvents.stream()
                    .sorted((o1, o2) -> {
                        //按updateTime升序排列
                        LocalDateTime o1Time = o1.getUpdateTime() == null ? o1.getCreateTime() : o1.getUpdateTime();
                        LocalDateTime o2Time = o2.getUpdateTime() == null ? o2.getCreateTime() : o2.getUpdateTime();
                        return o1Time.compareTo(o2Time);
                    })
                    .filter(ep -> !ep.getAskeventstatus().equals(AskEventStatus.PENDING)
                            && !ep.getAskeventstatus().equals(AskEventStatus.SUCCESS))
                    .findFirst();
            if (failedEventPublish.isPresent()) {
                // 查询到第一个不为PENDING也不为SUCCESS状态的askEvent, 根据这个状态设置UnionEventWatch的状态
                failedStatus = failedEventPublish.get().getAskeventstatus();
                unitedFailedInfo = new FailureInfo(EventUtils.fromAskEventStatus(failedStatus),
                        failedEventPublish.get().getUpdateTime());
            }

        }

        if (failedStatus != null) {

            eventWatch.setAskeventstatus(failedStatus);
            watchDao.update(eventWatch);

            //修改状态为PENDING或PENDING的askEvent到这个失败状态, 并且如果askEvent可以撤销, 进行撤销
            List<AskRequstEventPublish> failedEventProcessList = askEvents.stream()
                    .filter(ep -> AskEventStatus.PENDING == ep.getAskeventstatus()
                            || AskEventStatus.SUCCESS == ep.getAskeventstatus())
                    .collect(Collectors.toList());
            for (AskRequstEventPublish ep : failedEventProcessList) {
                ep.setAskeventstatus(failedStatus);
                askRequestEventPublishRepository.update(ep);
                if (eventRegistry.isEventRevokable(ep.getEventtype())) {
                    //撤销操作
                    eventBus.publishRevokeEvent(ep.getEventid(), unitedFailedInfo);
                }
            }

            // 执行失败的回调函数
            executeCallback(false, callbackClassName, extraParams, askEvents, unitedFailedInfo);
        }

    }

    /**
     * 执行回调函数
     *
     * @param success
     * @param callbackClassName
     * @param extraParams
     * @param askEvents
     * @param failureInfo
     */

    private void executeCallback(boolean success, String callbackClassName, String extraParams,
                                 List<AskRequstEventPublish> askEvents, FailureInfo failureInfo) {

        if (!StringUtils.hasText(callbackClassName)) return;


        AskEventCallback askEventCallback = EventRegistry.getAskEventCallback(callbackClassName);

        if (log.isDebugEnabled()) {
            log.debug("execute callback method, askEventCallback: {}, success: {}, askEvents size: {}",
                    askEventCallback, success, askEvents.size());
        }
        askEventCallback.call(eventRegistry, success, askEvents, extraParams, failureInfo);


    }

    @Transactional(readOnly = true)
    public List<EventWatchProcess> findUnprocessedEventWatchProcess() {
        List<EventWatchProcess> eventWatchProcessList = fetchAllFromQueue();
        if (firstTime.compareAndSet(true, false)) {
            List<EventWatchProcess> list = eventWatchProcessRepository.fetchByStatus(ProcessStatus.NEW);
            log.debug("first time to findUnprocessedEventWatchProcess, " +
                    "search unprocessed EventWatchProcess from db, size: " + list.size());
            eventWatchProcessList.addAll(list);
        }
        //按createTime降序排列
        return eventWatchProcessList.stream()
                .sorted((p1, p2) -> Math.negateExact(p1.getCreateTime().compareTo(p2.getCreateTime())))
                .collect(Collectors.toList());

    }

    @Transactional
    public void updateStatusBatchToProcessed(final int[] a) {
        eventWatchProcessRepository.updateStatusBatch(a, ProcessStatus.PROCESSED);
    }

    @Transactional(readOnly = true)
    public List<EventWatch> findTimeoutEventWatch(LocalDateTime timeoutTime) {

        return watchDao.findByAskEventStatusAndTimeoutTimeBefore(AskEventStatus.PENDING, timeoutTime);

    }


    /**
     * 往队列放入元素
     *
     * @param eventWatchProcess
     * @return
     */

    public boolean addToQueue(EventWatchProcess eventWatchProcess) {

        try {
            if (log.isDebugEnabled()) {
                log.debug("add EVENT_WATCH_PROCESS to queue, EVENT_WATCH_PROCESS: " + eventWatchProcess);
            }
            queue.offer(eventWatchProcess, 1, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            log.error("往队列放消息的阻塞过程被中断,队列已满?", e);
        }
        return false;
    }

    /**
     * 取出队列中的所有元素
     *
     * @return
     */

    private List<EventWatchProcess> fetchAllFromQueue() {
        List<EventWatchProcess> allMessages = new ArrayList<>();
        queue.drainTo(allMessages);
        return allMessages;
    }


}

