package io.github.daviszhao.stonemason.busEvent.services.old;

import com.google.common.base.Stopwatch;
import io.github.daviszhao.stonemason.busEvent.constants.AskEventStatus;
import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.busEvent.event.AskEvent;
import io.github.daviszhao.stonemason.busEvent.event.AskResponseEvent;
import io.github.daviszhao.stonemason.busEvent.event.NotifyEvent;
import io.github.daviszhao.stonemason.busEvent.event.RevokeAskEvent;
import io.github.daviszhao.stonemason.busEvent.payloads.FailureInfo;
import io.github.daviszhao.stonemason.busEvent.payloads.FailureReason;
import io.github.daviszhao.stonemason.db.event.tables.daos.*;
import io.github.daviszhao.stonemason.exceptions.event.EventException;
import io.github.daviszhao.stonemason.models.event.AskRequstEventPublish;
import io.github.daviszhao.stonemason.models.event.EventProcess;
import io.github.daviszhao.stonemason.models.event.EventWatchProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
public class EventProcesser {
    private static class EventHandlerResponse<T> {

        String errorMessage;
        private T value;

        public EventHandlerResponse(T value, String errorMessage) {
            this.value = value;
            this.errorMessage = errorMessage;
        }

        public T getValue() {
            return value;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    private class EventHandlerExecutor {
    }/*

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
    private EventActivator eventActivator;
    @Inject
    private ApplicationEventPublisher applicationEventPublisher;
    @Inject
    private TaskExecutor taskExecutor;
    @Inject
    private EventBus eventBus;
    @Inject
    private EventRegistry eventRegistry;
    @Inject
    private EventHandlerExecutor eventHandlerExecutor;
    @Inject
    private EventProcessDao eventProcessRepository;

    @Inject
    private EventProcesser self;

    Optional<EventWatchProcess> processAskResponseEvent(EventProcess event, EventBus eventBus) {

        AskResponseEvent askResponseEvent = eventRegistry.deserializeAskResponseEvent(event.getPayload());
        String askEventId = askResponseEvent.getAskEventId();
        AskRequstEventPublish AskRequstEventPublish = eventPublishService.getAskRequestEventByEventId(askEventId);
        if (!AskRequstEventPublish.getAskeventstatus().equals(AskEventStatus.PENDING)) {
            return Optional.empty();
        }

        AskEventStatus askEventStatus;
        FailureInfo failureInfo = null;
        if (askResponseEvent.isSuccess()) {
            askEventStatus = AskEventStatus.SUCCESS;
        } else {
            askEventStatus = AskEventStatus.FAILED;
            failureInfo = new FailureInfo(FailureReason.FAILED, LocalDateTime.now());
        }
        AskRequstEventPublish.setAskeventstatus(askEventStatus);
        askRequestEventPublishRepository.save(AskRequstEventPublish);

        return eventWatchService.processEventWatch(AskRequstEventPublish.getWatchId(), askEventStatus, failureInfo);

    }

    void processRevokeEvent(EventProcess event, EventBus eventBus) {

        RevokeAskEvent revokeAskEvent = (RevokeAskEvent) eventRegistry.deserializeEvent(
                RevokeAskEvent.EVENT_TYPE,
                event.getPayload());

        EventProcess askEventProcess = eventProcessRepository.fetchOneByEventid(revokeAskEvent.getAskEventId());
        if (askEventProcess == null) {
            throw new EventException(String.format("根据事件ID[%d]没有找到EventProcess", revokeAskEvent.getAskEventId()));
        }

        String type = askEventProcess.getEventtype();
        Set<RevokableAskEventHandler> eventHandlers = eventRegistry.getRevokableAskEventHandlers(type);
        if (eventHandlers == null || eventHandlers.isEmpty()) {
            log.error(String.format("EventProcess[id=%d, type=%s, payload=%s]的eventHandlers列表为空'",
                    askEventProcess.getId(), type, askEventProcess.getPayload()));
            return;
        }

        AskEvent originEvent = (AskEvent) eventRegistry.deserializeEvent(type, askEventProcess.getPayload());

        eventHandlers.forEach(
                handler -> executeEventHandler(
                        event.getId(),
                        () -> {
                            handler.processRevoke(originEvent, revokeAskEvent.getFailureInfo());
                            return null;
                        },
                        null
                )
        );
    }

    void processAskEvent(EventProcess event, EventBus eventBus) {

        String type = event.getEventtype();

        Set<AskEventHandler> eventHandlers = eventRegistry.getAskEventHandlers(type);
        if (eventHandlers == null || eventHandlers.isEmpty()) {
            log.error(String.format("EventProcess[id=%d, type=%s, payload=%s]的eventHandlers列表为空'",
                    event.getId(), type, event.getPayload()));
            return;
        }

        AskEvent askEvent = (AskEvent) eventRegistry.deserializeEvent(type, event.getPayload());

        eventHandlers.forEach(handler -> {
            EventHandlerResponse<BooleanWrapper> result = executeEventHandler(event.getId(),
                    () -> handler.processRequest(askEvent), new BooleanWrapper(false));
            createAskResponse(askEvent, result.getValue().isSuccess(), result.getValue().getMessage());
        });

    }

    void processNotifyEvent(EventProcess event, EventBus eventBus) {

        String type = event.getEventtype();

        Set<NotifyEventHandler> eventHandlers = eventRegistry.getNotifyEventHandlers(type);
        if (eventHandlers == null || eventHandlers.isEmpty()) {
            log.error(String.format("EventProcess[id=%d, type=%s, payload=%s]的eventHandlers列表为空'",
                    event.getId(), type, event.getPayload()));
            return;
        }

        NotifyEvent notifyEvent = (NotifyEvent) eventRegistry.deserializeEvent(type, event.getPayload());

        eventHandlers.forEach(
                handler -> executeEventHandler(
                        event.getId(),
                        () -> {
                            handler.notify(notifyEvent);
                            return null;
                        },
                        null));

    }



    @Transactional
    public Optional<EventWatchProcess> handleEventProcess(Integer eventProcessId, EventBus eventBus) {
        Optional<EventWatchProcess> eventWatchProcessOptional = Optional.empty();

        EventProcess eventProcess = eventProcessRepository.fetchOneById(eventProcessId);
        if (ProcessStatus.NEW != eventProcess.getStatus()) {
            //已经被处理过了, 忽略
            return eventWatchProcessOptional;
        }

        EventCategory eventcategory = eventProcess.getEventcategory();
        log.debug("handle event process, id: {}, event category: {} ", eventProcessId, eventcategory);

        switch (eventcategory) {
            case NOTIFY:
                processNotifyEvent(eventProcess, eventBus);
                break;
            case ASK:
                processAskEvent(eventProcess, eventBus);
                break;
            case REVOKE:
                processRevokeEvent(eventProcess, eventBus);
                break;
            case ASKRESP:
                eventWatchProcessOptional = processAskResponseEvent(eventProcess, eventBus);
                break;
            default:
                throw new EventException(String.format("unknown event category, process id: %d, event category: %s ",
                        eventProcessId, eventcategory));
        }
        eventProcess.setStatus(ProcessStatus.PROCESSED);
        eventProcessRepository.update(eventProcess);

        return eventWatchProcessOptional;
    }

    @Transactional
    public EventProcess recordEvent(String message, EventBus eventBus) {
        Map<String, Object> eventMap = EventUtils.retrieveEventMapFromJson(message);
        String eventType = String.valueOfIgnoreCase((String) eventMap.get("type"));
        EventCategory eventCategory = eventRegistry.getEventCategoryByType(eventType);
        if (eventCategory.equals(EventCategory.ASKRESP) || eventCategory.equals(EventCategory.REVOKE)) {
            String askEventId = (String) eventMap.get("askEventId");
            if (askEventId == null) {
                throw new EventException("EventCategory为ASKRESP或REVOKE的事件, askEventId为null, payload: " + message);
            }
            boolean eventPublishNotExist = true;
            if (eventCategory.equals(EventCategory.ASKRESP)) {
                eventPublishNotExist = askRequestEventPublishRepository.fetchOneByEventid(askEventId) == null;
            } else if (eventCategory.equals(EventCategory.REVOKE)) {
                eventPublishNotExist = askResponseEventPublishRepository.countByAskEventId(askEventId) == 0L;
            }
            if (eventPublishNotExist) {
                //如果为ASKRESP或REVOKE事件并且请求id在数据库不存在, 则忽略这个事件
                return null;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("receive message from kafka: {}", message);
        }

        EventProcess eventProcess = new EventProcess();
        eventProcess.setPayload(message);
        eventProcess.setEventid((String) eventMap.get("id"));
        eventProcess.setEventtype(eventType);
        eventProcess.setEventcategory(eventCategory);

        eventProcessRepository.insert(eventProcess);
        return eventProcess;
    }

    private <T> EventHandlerResponse<T> executeEventHandler(Long eventProcessId, Supplier<T> supplier, T defaultValue, EventBus eventBus) {

        T value = defaultValue;
        String errorMessage = null;
        Stopwatch stopwatch = null;
        try {
            if (log.isDebugEnabled()) {
                stopwatch = Stopwatch.createStarted();
            }
            //开启新事务, 防止handler执行方法报错导致整体事务回滚
            value = eventHandlerExecutor.executeEventHandler(supplier);
        } catch (TransactionSystemException ignore) {

        } catch (AppBusinessException e) {
            errorMessage = e.getMessage();
        } catch (Exception e) {
            log.error("", e);
            errorMessage = e.getMessage();
        } finally {
            if (log.isDebugEnabled() && stopwatch != null) {
                stopwatch.stop();
                log.debug(String.format("执行事件回调结束耗时%dms, EventProcess[id=%d]",
                        stopwatch.elapsed(TimeUnit.MILLISECONDS), eventProcessId));
            }
        }

        return new EventHandlerResponse<>(value, errorMessage);
    }
*/}
