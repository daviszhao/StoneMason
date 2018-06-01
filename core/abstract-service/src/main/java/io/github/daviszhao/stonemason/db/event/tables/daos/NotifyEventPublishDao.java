
package io.github.daviszhao.stonemason.db.event.tables.daos;


import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.busEvent.payloads.EventPayload;
import io.github.daviszhao.stonemason.db.base.daos.AbstractDao;
import io.github.daviszhao.stonemason.db.event.tables.NotifyEventPublishTable;
import io.github.daviszhao.stonemason.db.event.tables.records.NotifyEventPublishRecord;
import io.github.daviszhao.stonemason.models.event.NotifyEventPublish;
import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.util.List;


@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.6"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
@Repository
public class NotifyEventPublishDao extends AbstractDao<NotifyEventPublishRecord, NotifyEventPublish, Integer> {

    /**
     * Create a new NotifyEventPublishDao without any configuration
     */
    public NotifyEventPublishDao() {
        super(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH, NotifyEventPublish.class);
    }

    /**
     * Create a new NotifyEventPublishDao with an attached configuration
     */
    @Autowired
    public NotifyEventPublishDao(Configuration configuration) {
        super(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH, NotifyEventPublish.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(NotifyEventPublish object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<NotifyEventPublish> fetchById(Integer... values) {
        return fetch(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public NotifyEventPublish fetchOneById(Integer value) {
        return fetchOne(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.ID, value);
    }

    /**
     * Fetch records that have <code>payload IN (values)</code>
     */
    public List<NotifyEventPublish> fetchByPayload(EventPayload... values) {
        return fetch(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.PAYLOAD, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<NotifyEventPublish> fetchByStatus(ProcessStatus... values) {

        return fetch(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.STATUS, values);
    }

    /**
     * Fetch records that have <code>eventId IN (values)</code>
     */
    public List<NotifyEventPublish> fetchByEventid(String... values) {
        return fetch(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.EVENTID, values);
    }

    /**
     * Fetch a unique record that has <code>eventId = value</code>
     */
    public NotifyEventPublish fetchOneByEventid(String value) {
        return fetchOne(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.EVENTID, value);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<NotifyEventPublish> fetchByVersion(Integer... values) {
        return fetch(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.VERSION, values);
    }

    /**
     * Fetch records that have <code>eventType IN (values)</code>
     */
    public List<NotifyEventPublish> fetchByEventtype(String... values) {
        return fetch(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.EVENTTYPE, values);
    }
}
