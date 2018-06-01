package io.github.daviszhao.stonemason.busEvent.services.old;

import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.db.event.tables.daos.AskRequstEventPublishDao;
import io.github.daviszhao.stonemason.db.event.tables.daos.AskResponseEventPublishDao;
import io.github.daviszhao.stonemason.db.event.tables.daos.NotifyEventPublishDao;
import io.github.daviszhao.stonemason.db.event.tables.daos.RevokeAskEventPublishDao;
import io.github.daviszhao.stonemason.exceptions.event.EventException;
import io.github.daviszhao.stonemason.models.event.AbstractEventPublish;
import io.github.daviszhao.stonemason.models.event.AskRequstEventPublish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

 @Slf4j
class EventPublishService {

    @Inject
    private NotifyEventPublishDao notifyDao;

    @Inject
    private AskRequstEventPublishDao askDao;

    @Inject
    private RevokeAskEventPublishDao revokeDao;

    @Inject
    private AskResponseEventPublishDao asdRespDao;


    @Transactional(readOnly = true)
    public List<AbstractEventPublish> findUnpublishedEvent() {
        List<AbstractEventPublish> unpublishedEvents = new ArrayList<>();
        unpublishedEvents.addAll(notifyDao.fetchByStatus(ProcessStatus.NEW));
        unpublishedEvents.addAll(askDao.fetchByStatus(ProcessStatus.NEW));
        unpublishedEvents.addAll(revokeDao.fetchByStatus(ProcessStatus.NEW));
        unpublishedEvents.addAll(asdRespDao.fetchByStatus(ProcessStatus.NEW));
        return unpublishedEvents;
    }

    @Transactional(readOnly = true)
    public AskRequstEventPublish getAskRequestEventByEventId(String eventId) {
        AskRequstEventPublish askRequestEventPublish = askDao.fetchOneByEventid(eventId);
        if (askRequestEventPublish == null) {
            throw new EventException(String.format("根据事件ID[%d]没有找到AskRequestEventPublish", eventId));
        }
        return askRequestEventPublish;
    }

    @Transactional(readOnly = true)
    public List<AskRequstEventPublish> findAskRequestEventByEventId(List<String> eventIds) {
        assert !CollectionUtils.isEmpty(eventIds);
        Map<String, AskRequstEventPublish> map = askDao.fetchByEventid(eventIds)
                .stream()
                .collect(Collectors.toMap(AskRequstEventPublish::getEventid, Function.identity()));

        Set<String> eventNotExistIdSet = new HashSet<>();

        List<AskRequstEventPublish> askRequestEventPublishList = eventIds.stream().map(eventId -> {
            AskRequstEventPublish p = map.get(eventId);
            if (p == null) eventNotExistIdSet.add(eventId);
            return p;
        }).collect(Collectors.toList());

        if (!eventNotExistIdSet.isEmpty()) {
            throw new EventException(String.format("根据事件ID[%s]没有找到AskRequestEventPublish", eventNotExistIdSet));
        }

        return askRequestEventPublishList;
    }


}