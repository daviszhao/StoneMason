
package io.github.daviszhao.stonemason.db.event.tables.daos;


import io.github.daviszhao.stonemason.db.base.daos.AbstractDao;
import io.github.daviszhao.stonemason.db.event.tables.EventWatchProcessTable;
import io.github.daviszhao.stonemason.db.event.tables.records.EventWatchProcessRecord;
import io.github.daviszhao.stonemason.models.event.EventWatchProcess;
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
public class EventWatchProcessDao extends AbstractDao<EventWatchProcessRecord, EventWatchProcess, Integer> {

    /**
     * Create a new EventWatchProcessDao without any configuration
     */
    public EventWatchProcessDao() {
        super(EventWatchProcessTable.eventWatchProcess, EventWatchProcess.class);
    }

    /**
     * Create a new EventWatchProcessDao with an attached configuration
     */
    @Autowired
    public EventWatchProcessDao(Configuration configuration) {
        super(EventWatchProcessTable.eventWatchProcess, EventWatchProcess.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(EventWatchProcess object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<EventWatchProcess> fetchById(Integer... values) {
        return fetch(EventWatchProcessTable.eventWatchProcess.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public EventWatchProcess fetchOneById(Integer value) {
        return fetchOne(EventWatchProcessTable.eventWatchProcess.ID, value);
    }

    /**
     * Fetch records that have <code>watchId IN (values)</code>
     */
    public List<EventWatchProcess> fetchByWatchid(Integer... values) {
        return fetch(EventWatchProcessTable.eventWatchProcess.WATCHID, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<EventWatchProcess> fetchByStatus(String... values) {
        return fetch(EventWatchProcessTable.eventWatchProcess.STATUS, values);
    }

    /**
     * Fetch records that have <code>failureInfo IN (values)</code>
     */
    public List<EventWatchProcess> fetchByFailureinfo(String... values) {
        return fetch(EventWatchProcessTable.eventWatchProcess.FAILUREINFO, values);
    }
}