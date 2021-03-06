
package io.github.daviszhao.stonemason.db.event.tables.daos;


import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.db.base.daos.AbstractDao;
import io.github.daviszhao.stonemason.db.event.tables.EventProcessTable;
import io.github.daviszhao.stonemason.db.event.tables.records.EventProcessRecord;
import io.github.daviszhao.stonemason.models.event.EventProcess;
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
public class EventProcessDao extends AbstractDao<EventProcessRecord, EventProcess, Integer> {

    /**
     * Create a new EventProcessDao without any configuration
     */
    public EventProcessDao() {
        super(EventProcessTable.EVENT_PROCESS, EventProcess.class);
    }

    /**
     * Create a new EventProcessDao with an attached configuration
     */
    @Autowired
    public EventProcessDao(Configuration configuration) {
        super(EventProcessTable.EVENT_PROCESS, EventProcess.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(EventProcess object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<EventProcess> fetchById(Integer... values) {
        return fetch(EventProcessTable.EVENT_PROCESS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public EventProcess fetchOneById(Integer value) {
        return fetchOne(EventProcessTable.EVENT_PROCESS.ID, value);
    }

    /**
     * Fetch records that have <code>payload IN (values)</code>
     */
    public List<EventProcess> fetchByPayload(String... values) {
        return fetch(EventProcessTable.EVENT_PROCESS.PAYLOAD, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<EventProcess> fetchByStatus(ProcessStatus... values) {
        return fetch(EventProcessTable.EVENT_PROCESS.STATUS, values);
    }

    /**
     * Fetch records that have <code>eventCategory IN (values)</code>
     */
    public List<EventProcess> fetchByEventcategory(EventCategory... values) {
        return fetch(EventProcessTable.EVENT_PROCESS.EVENTCATEGORY, values);
    }

    /**
     * Fetch records that have <code>eventId IN (values)</code>
     */
    public List<EventProcess> fetchByEventid(String... values) {
        return fetch(EventProcessTable.EVENT_PROCESS.EVENTID, values);
    }

    /**
     * Fetch a unique record that has <code>eventId = value</code>
     */
    public EventProcess fetchOneByEventid(String value) {
        return fetchOne(EventProcessTable.EVENT_PROCESS.EVENTID, value);
    }

    /**
     * Fetch records that have <code>eventType IN (values)</code>
     */
    public List<EventProcess> fetchByEventtype(String... values) {
        return fetch(EventProcessTable.EVENT_PROCESS.EVENTTYPE, values);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<EventProcess> fetchByVersion(Integer... values) {
        return fetch(EventProcessTable.EVENT_PROCESS.VERSION, values);
    }
}
