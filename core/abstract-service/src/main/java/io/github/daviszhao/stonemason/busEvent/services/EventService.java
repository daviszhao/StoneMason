package io.github.daviszhao.stonemason.busEvent.services;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.busEvent.event.NotifyEvent;
import io.github.daviszhao.stonemason.busEvent.services.utils.EventUtils;
import io.github.daviszhao.stonemason.db.event.tables.daos.EventProcessDao;
import io.github.daviszhao.stonemason.db.event.tables.daos.NotifyEventPublishDao;
import io.github.daviszhao.stonemason.models.event.EventProcess;
import io.github.daviszhao.stonemason.models.event.NotifyEventPublish;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Slf4j
@Named
@AllArgsConstructor
public class EventService {
    private NotifyEventPublishDao notifyDao;
    private EventProcessDao processDao;
    private ApplicationContext context;
    private EventUtils eventUtils;

    @Transactional
    public void prepairNotifyEvent(String type, Serializable payload) {
        NotifyEventPublish publish = new NotifyEventPublish();
        publish.setEventtype(type);
        publish.setPayload(eventUtils.serialize(payload));
        notifyDao.insert(publish);
    }

    public List<NotifyEventPublish> getAllUnpublishedEvent() {
        return notifyDao.fetchByStatus(ProcessStatus.NEW);
    }

    private void publish(ApplicationEvent event) {
        context.publishEvent(event);
    }

    private String getInstanceId() {
        return this.context.getId();
    }

    @Transactional
    public void publishEvent(NotifyEventPublish eventPublish) {
        NotifyEvent event = new NotifyEvent(this, getInstanceId(), eventPublish.getPayload(), eventPublish.getEventtype());
//                eventPublish.getEventtype(), oriservice, this, eventPublish.getPayload());
        eventPublish.setEventid(event.getId());
        try {

            publish(event);
            eventPublish.setStatus(ProcessStatus.PROCESSED);
            notifyDao.update(eventPublish);
        } catch (Exception e) {
            log.error("发布事件出错。", e);
        }
    }


    public List<EventProcess> getUnproccessedEventProcesses() {
        return processDao.fetchByStatus(ProcessStatus.NEW);
    }

    @Transactional
    public <E extends AbstractBusEvent> void receivedEvent(E event) {
        EventProcess process = processDao.fetchOneByEventid(event.getId());
        if (process == null) {//新事件
            process = new EventProcess(event.getEventType(), event.getCategory(), event.getPayLoad(), event.getId());
            processDao.insert(process);
        }
    }

    public void updateEventProcess(EventProcess eventProcess) {
        processDao.update(eventProcess);
    }
}
