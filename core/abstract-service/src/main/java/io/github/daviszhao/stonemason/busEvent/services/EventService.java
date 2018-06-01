package io.github.daviszhao.stonemason.busEvent.services;

import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.busEvent.event.AskEvent;
import io.github.daviszhao.stonemason.busEvent.event.AskResponseEvent;
import io.github.daviszhao.stonemason.busEvent.event.NotifyEvent;
import io.github.daviszhao.stonemason.busEvent.event.RevokeAskEvent;
import io.github.daviszhao.stonemason.busEvent.payloads.EventPayload;
import io.github.daviszhao.stonemason.db.event.tables.daos.EventProcessDao;
import io.github.daviszhao.stonemason.db.event.tables.daos.NotifyEventPublishDao;
import io.github.daviszhao.stonemason.models.event.EventProcess;
import io.github.daviszhao.stonemason.models.event.NotifyEventPublish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Named
public class EventService {
    @Inject
    EventConsumer consumer;
    @Inject
    private NotifyEventPublishDao notifyDao;
    @Inject
    private EventProcessDao processDao;
    @Inject
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void prepairNotifyEvent(String type, EventPayload payload) {
        NotifyEventPublish publish = new NotifyEventPublish(type, payload);
        notifyDao.insert(publish);
    }

    @Transactional
    public void receivedEvent(NotifyEvent event) {
        EventProcess process = processDao.fetchOneByEventid(event.getId());
        if (process == null) {//新事件
            process = new EventProcess(event.getType(), event.getCategory(), event.getPayLoad(), event.getId());
            processDao.insert(process);
        }
    }

    public List<NotifyEventPublish> getAllUnpublishedEvent() {
        return notifyDao.fetchByStatus(ProcessStatus.NEW);
    }

    @Transactional
    public void publishEvent(NotifyEventPublish eventPublish) {
        NotifyEvent event;
        if (hasText(eventPublish.getEventid()))
            event = new NotifyEvent(eventPublish.getPayload(), eventPublish.getEventid(), eventPublish.getEventtype());
        else {
            event = new NotifyEvent(eventPublish.getPayload(), eventPublish.getEventtype());
            eventPublish.setEventid(event.getId());
        }
        try {
            applicationEventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("发布事件出错。");
        }
        eventPublish.setStatus(ProcessStatus.PROCESSED);
        notifyDao.update(eventPublish);
    }

    @Transactional
    public void process(EventProcess eventProcess) {
        consumer.process(eventProcess.getEventcategory(), eventProcess.getPayload(), eventProcess.getEventtype());
        eventProcess.setStatus(ProcessStatus.PROCESSED);
        processDao.update(eventProcess);
    }

    public List<EventProcess> getUnproccessedEventProcesses() {
        return processDao.fetchByStatus(ProcessStatus.NEW);
    }

    @Transactional
    public void receivedEvent(RevokeAskEvent event) {
    }

    @Transactional
    public void receivedEvent(AskResponseEvent event) {
    }

    @Transactional
    public void receivedEvent(AskEvent event) {
    }
}
