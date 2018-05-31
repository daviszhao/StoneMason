
package io.github.daviszhao.stonemason.db.event.tables.daos;


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
        super(EventProcessTable.eventProcess, EventProcess.class);
    }

    /**
     * Create a new EventProcessDao with an attached configuration
     */
    @Autowired
    public EventProcessDao(Configuration configuration) {
        super(EventProcessTable.eventProcess, EventProcess.class, configuration);
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
        return fetch(EventProcessTable.eventProcess.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public EventProcess fetchOneById(Integer value) {
        return fetchOne(EventProcessTable.eventProcess.ID, value);
    }

    /**
     * Fetch records that have <code>payload IN (values)</code>
     */
    public List<EventProcess> fetchByPayload(String... values) {
        return fetch(EventProcessTable.eventProcess.PAYLOAD, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<EventProcess> fetchByStatus(String... values) {
        return fetch(EventProcessTable.eventProcess.STATUS, values);
    }

    /**
     * Fetch records that have <code>eventCategory IN (values)</code>
     */
    public List<EventProcess> fetchByEventcategory(String... values) {
        return fetch(EventProcessTable.eventProcess.EVENTCATEGORY, values);
    }

    /**
     * Fetch records that have <code>eventId IN (values)</code>
     */
    public List<EventProcess> fetchByEventid(String... values) {
        return fetch(EventProcessTable.eventProcess.EVENTID, values);
    }

    /**
     * Fetch a unique record that has <code>eventId = value</code>
     */
    public EventProcess fetchOneByEventid(String value) {
        return fetchOne(EventProcessTable.eventProcess.EVENTID, value);
    }

    /**
     * Fetch records that have <code>eventType IN (values)</code>
     */
    public List<EventProcess> fetchByEventtype(String... values) {
        return fetch(EventProcessTable.eventProcess.EVENTTYPE, values);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<EventProcess> fetchByVersion(Integer... values) {
        return fetch(EventProcessTable.eventProcess.VERSION, values);
    }
}