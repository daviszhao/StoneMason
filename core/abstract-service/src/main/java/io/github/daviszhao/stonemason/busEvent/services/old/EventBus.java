package io.github.daviszhao.stonemason.busEvent.services.old;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.base.Revokable;
import io.github.daviszhao.stonemason.busEvent.constants.AskEventStatus;
import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.busEvent.event.AskEvent;
import io.github.daviszhao.stonemason.busEvent.event.AskResponseEvent;
import io.github.daviszhao.stonemason.busEvent.event.NotifyEvent;
import io.github.daviszhao.stonemason.busEvent.event.RevokeAskEvent;
import io.github.daviszhao.stonemason.busEvent.payloads.FailureInfo;
import io.github.daviszhao.stonemason.busEvent.payloads.FailureReason;
import io.github.daviszhao.stonemason.db.event.tables.daos.*;
import io.github.daviszhao.stonemason.exceptions.event.EventException;
import io.github.daviszhao.stonemason.models.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class EventBus {
    @Inject
    private AskRequstEventPublishDao askRequestEventPublishRepository;
    @Inject
    private AskResponseEventPublishDao askResponseEventPublishRepository;

    @Inject
    private NotifyEventPublishDao notifyEventPublishRepository;
    @Inject
    private RevokeAskEventPublishDao revokeAskEventPublishRepository;
    @Inject
    private EventWatchService eventWatchService;
    @Inject
    private EventPublishService eventPublishService;
    @Inject
    private ApplicationEventPublisher applicationEventPublisher;
    @Inject
    private EventBus self;
    private EventProcessDao eventProcessRepository;

    /**
     * 发布Notify事件
     *
     * @param notifyEvent
     * @return
     */
    @Transactional
    public NotifyEventPublish publish(NotifyEvent notifyEvent) {

        fillEventId(notifyEvent);
        String payload = EventUtils.serializeEvent(notifyEvent, NotifyEvent.class);

        NotifyEventPublish eventPublish = new NotifyEventPublish();
        eventPublish.setPayload(payload);
        eventPublish.setEventid(notifyEvent.getId());
        eventPublish.setEventtype(notifyEvent.getType());

        notifyEventPublishRepository.insert(eventPublish);
        return eventPublish;
    }

    /**
     * 发布ask事件
     *
     * @param askParameter
     * @return
     */
    @Transactional
    public List<AskRequstEventPublish> ask(AskParameter askParameter) {

        askParameter.getAskEvents().forEach(this::fillEventId);

        EventWatch eventWatch = eventWatchService.watchAskEvents(askParameter);

        return askParameter.getAskEvents().stream().map(askEvent -> {
            AskRequstEventPublish eventPublish = new AskRequstEventPublish();
            eventPublish.setEventid(askEvent.getId());
            eventPublish.setEventtype(askEvent.getType());
            eventPublish.setAskeventstatus(AskEventStatus.PENDING);
            eventPublish.setWatchid(eventWatch.getId());
            eventPublish.setPayload(EventUtils.serializeEvent(askEvent, AskEvent.class));

            askRequestEventPublishRepository.insert(eventPublish);

            return eventPublish;

        }).collect(Collectors.toList());
    }

    /**
     * 尝试对事件进行撤销
     *
     * @param askEvent
     * @return
     */
    @Transactional
    public void revoke(AskEvent askEvent, FailureInfo failureInfo) {
        if (!(askEvent instanceof Revokable)) {
            throw new EventException(String.format("类型为%s的事件不能撤销", askEvent.getClass()));
        }
        if (askEvent.getId() == null) {
            throw new EventException("ID为空, 新事件不能撤销");
        }
        AskRequstEventPublish eventPublish = eventPublishService.getAskRequestEventByEventId(askEvent.getId());

        if (ProcessStatus.NEW == eventPublish.getStatus()) {
            //首先判断原事件有没有发送, 如果没有发送就不发送了
            eventPublish.setStatus(ProcessStatus.IGNORE);
            askRequestEventPublishRepository.update(eventPublish);
        } else if (AskEventStatus.PENDING == eventPublish.getAskeventstatus()
                || AskEventStatus.SUCCESS == eventPublish.getAskeventstatus()) {

            if (ProcessStatus.PROCESSED == eventPublish.getStatus()) {
                publishRevokeEvent(askEvent.getId(), failureInfo);
            }

            //改变之前事件的状态
            AskEventStatus revokeAskEventStatus = AskEventStatus.FAILED;
            if (failureInfo != null && failureInfo.getReason() != null) {
                revokeAskEventStatus = EventUtils.fromFailureReason(failureInfo.getReason());
            }
            eventPublish.setAskeventstatus(revokeAskEventStatus);

            askRequestEventPublishRepository.update(eventPublish);

            //TODO AskRequstEventPublish 状态已经改变, 根据watchId判断eventWatch是不是也要改变

        }
    }

    /**
     * 发布撤销事件
     *
     * @param askEventId
     * @param failureInfo
     */
    @Transactional
    public void publishRevokeEvent(String askEventId, FailureInfo failureInfo) {
        RevokeAskEvent revokeAskEvent = new RevokeAskEvent(failureInfo, askEventId);
        fillEventId(revokeAskEvent);

        RevokeAskEventPublish revokeAskEventPublish = new RevokeAskEventPublish();
        revokeAskEventPublish.setAskeventid(askEventId);
        revokeAskEventPublish.setEventid(revokeAskEvent.getId());
        revokeAskEventPublish.setEventtype(revokeAskEvent.getType());
        revokeAskEventPublish.setPayload(EventUtils.serializeEvent(revokeAskEvent, RevokeAskEvent.class));

        revokeAskEventPublishRepository.insert(revokeAskEventPublish);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public void sendUnpublishedEvent() {

        List<? extends AbstractEventPublish> events = eventPublishService.findUnpublishedEvent();
//        logger.info("待发布事件数量: " + events.size());

        for (AbstractEventPublish event : events)
            try {
                //eventActivator.sendMessage抛异常不会导致整个事务回滚
                publishEvent(event);
                event.setStatus(ProcessStatus.PROCESSED);
                updateEventPublish(event);

            } catch (EventException e) {
                log.error(e.getMessage());
            } catch (Exception e) {
                log.error(String.format("发送消息到队列的时候发生异常, EventPublish[id=%d, payload=%s]",
                        event.getId(), event.getPayload()), e);
            }
    }

    private void publishEvent(AbstractEventPublish eventPublish) {
        AbstractBusEvent event = EventUtils.deserializeEvent(eventPublish.getPayload(), AbstractBusEvent.class);
        applicationEventPublisher.publishEvent(event);
//        return eventActivator.sendMessage(eventPublish.getPayload(), eventPublish.getEventtype().name());
    }


    /**
     * 发送ask结果
     *
     * @param askEvent
     * @param success
     * @param message
     * @return
     */
    private AskResponseEventPublish createAskResponse(AskEvent askEvent, final boolean success, final String message) {
        AskResponseEvent askResponseEvent = new AskResponseEvent(success, message, askEvent.getId());
        fillEventId(askResponseEvent);
        AskResponseEventPublish eventPublish = new AskResponseEventPublish();
        eventPublish.setSuccess(success);
        eventPublish.setAskeventid(askEvent.getId());
        eventPublish.setEventtype(askResponseEvent.getType());
        eventPublish.setEventid(askResponseEvent.getId());
        eventPublish.setPayload(EventUtils.serializeEvent(askResponseEvent, AskResponseEvent.class));

        askResponseEventPublishRepository.insert(eventPublish);

        return eventPublish;
    }

    private void updateEventPublish(AbstractEventPublish eventPublish) {
        if (eventPublish instanceof NotifyEventPublish) {
            notifyEventPublishRepository.update((NotifyEventPublish) eventPublish);
//            notifyEventPublishRepository.flush();
        } else if (eventPublish instanceof AskRequstEventPublish) {
            askRequestEventPublishRepository.update((AskRequstEventPublish) eventPublish);
//            askRequestEventPublishRepository.flush();
        } else if (eventPublish instanceof AskResponseEventPublish) {
            askResponseEventPublishRepository.update((AskResponseEventPublish) eventPublish);
//            askResponseEventPublishRepository.flush();
        } else if (eventPublish instanceof RevokeAskEventPublish) {
            revokeAskEventPublishRepository.update((RevokeAskEventPublish) eventPublish);
//            revokeAskEventPublishRepository.flush();
        } else {
            throw new EventException(String.format("unknown eventPublish class: %s, id: %d",
                    eventPublish.getClass(), eventPublish.getId()));
        }
    }

    private void fillEventId(AbstractBusEvent event) {
        if (event.getId() != null) {
            throw new EventException("event id不为空, id:" + event.getId());
        }
        event.setId(UUID.randomUUID().toString());

    }


    //不在这里加事务注解, 因为想让这个方法内对service的调用都是独立事务.
    public void handleUnprocessedEventWatchProcess() {
        List<EventWatchProcess> eventWatchProcessList = eventWatchService.findUnprocessedEventWatchProcess();
//        logger.info("待处理eventWatchProcess数量: " + eventWatchProcessList.size());
        Set<Integer> successIdSet = new HashSet<>();
        Set<Integer> watchIdSet = new HashSet<>();
        for (EventWatchProcess eventWatchProcess : eventWatchProcessList) {
            try {
                if (watchIdSet.add(eventWatchProcess.getWatchid())) {
                    //processUnitedEventWatch方法内报异常只回滚内部事务
                    eventWatchService.processUnitedEventWatch(eventWatchProcess);
                }
                successIdSet.add(eventWatchProcess.getId());
            } catch (EventException e) {
                log.error(e.getMessage(), e);
                eventWatchService.addToQueue(eventWatchProcess);
                watchIdSet.remove(eventWatchProcess.getWatchid());
            } catch (Exception e) {
                log.error("处理unitedEventWatch事件的时候发生异常, EventWatchProcessId:" + eventWatchProcess.getId(), e);
                eventWatchService.addToQueue(eventWatchProcess);
                watchIdSet.remove(eventWatchProcess.getWatchid());
            }
        }

        if (!successIdSet.isEmpty()) {
            eventWatchService.updateStatusBatchToProcessed(successIdSet.stream().mapToInt(Integer::intValue).toArray());
        }
    }

    //不在这里加事务注解, 因为想让这个方法内对service的调用都是独立事务.
    public void handleTimeoutEventWatch() {
        LocalDateTime now = LocalDateTime.now();
        List<EventWatch> eventWatchList = eventWatchService.findTimeoutEventWatch(now);
        FailureInfo failureInfo = new FailureInfo(FailureReason.TIMEOUT, now);
        for (EventWatch eventWatch : eventWatchList) {
            try {
                eventWatchService.processEventWatch(eventWatch.getId(), AskEventStatus.TIMEOUT, failureInfo)
                        .map(eventWatchProcess -> eventWatchService.addToQueue(eventWatchProcess));
            } catch (EventException e) {
                log.error(e.getMessage());
            } catch (Exception e) {
                log.error(String.format("处理超时EventWatch的时候发生异常, id=%d",
                        eventWatch.getId()), e);
            }
        }
    }

    @Transactional
    public void searchAndHandleUnprocessedEvent() {
        log.debug("查找未处理的事件，并处理");/*
        List<EventProcess> eventProcesses = eventProcessRepository.fetchByStatus(ProcessStatus.NEW);
//        logger.info("待处理事件数量: " + eventProcesses.size());
        CountDownLatch latch = new CountDownLatch(eventProcesses.size());

        for (EventProcess event : eventProcesses) {
            final Integer eventProcessId = event.getId();
            taskExecutor.execute(() -> {
                try {
//                    EventBus eventBus = ApplicationContextHolder.context.getBean(class);
                    //handleEventProcess方法内报异常只回滚内部事务
                    self.handleEventProcess(eventProcessId, eventBus)
                            .map(eventWatchProcess -> eventWatchService.addToQueue(eventWatchProcess));
                } catch (EventException e) {
                    log.error(e.getMessage());
                } catch (Exception e) {
                    log.error(String.format("处理事件的时候发生异常, EventProcess[id=%d]",
                            eventProcessId), e);
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            //等待事件异步处理完成
            latch.await();
        } catch (InterruptedException e) {
            log.error("", e);
        }
*/
    }
}
