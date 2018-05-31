
package io.github.daviszhao.stonemason.db.event.tables.daos;


import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.db.base.daos.AbstractDao;
import io.github.daviszhao.stonemason.db.event.tables.AskResponseEventPublishTable;
import io.github.daviszhao.stonemason.db.event.tables.records.AskResponseEventPublishRecord;
import io.github.daviszhao.stonemason.models.event.AskResponseEventPublish;
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
public class AskResponseEventPublishDao extends AbstractDao<AskResponseEventPublishRecord, AskResponseEventPublish, Integer> {

    /**
     * Create a new AskResponseEventPublishDao without any configuration
     */
    public AskResponseEventPublishDao() {
        super(AskResponseEventPublishTable.askResponseEventPublish, AskResponseEventPublish.class);
    }

    /**
     * Create a new AskResponseEventPublishDao with an attached configuration
     */
    @Autowired
    public AskResponseEventPublishDao(Configuration configuration) {
        super(AskResponseEventPublishTable.askResponseEventPublish, AskResponseEventPublish.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(AskResponseEventPublish object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<AskResponseEventPublish> fetchById(Integer... values) {
        return fetch(AskResponseEventPublishTable.askResponseEventPublish.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public AskResponseEventPublish fetchOneById(Integer value) {
        return fetchOne(AskResponseEventPublishTable.askResponseEventPublish.ID, value);
    }

    /**
     * Fetch records that have <code>payload IN (values)</code>
     */
    public List<AskResponseEventPublish> fetchByPayload(String... values) {
        return fetch(AskResponseEventPublishTable.askResponseEventPublish.PAYLOAD, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<AskResponseEventPublish> fetchByStatus(ProcessStatus... values) {
        return fetch(AskResponseEventPublishTable.askResponseEventPublish.STATUS, values);
    }

    /**
     * Fetch records that have <code>eventId IN (values)</code>
     */
    public List<AskResponseEventPublish> fetchByEventid(String... values) {
        return fetch(AskResponseEventPublishTable.askResponseEventPublish.EVENTID, values);
    }

    /**
     * Fetch a unique record that has <code>eventId = value</code>
     */
    public AskResponseEventPublish fetchOneByEventid(String value) {
        return fetchOne(AskResponseEventPublishTable.askResponseEventPublish.EVENTID, value);
    }

    /**
     * Fetch records that have <code>success IN (values)</code>
     */
    public List<AskResponseEventPublish> fetchBySuccess(Boolean... values) {
        return fetch(AskResponseEventPublishTable.askResponseEventPublish.SUCCESS, values);
    }

    /**
     * Fetch records that have <code>askEventId IN (values)</code>
     */
    public List<AskResponseEventPublish> fetchByAskeventid(String... values) {
        return fetch(AskResponseEventPublishTable.askResponseEventPublish.ASKEVENTID, values);
    }

    /**
     * Fetch records that have <code>eventType IN (values)</code>
     */
    public List<AskResponseEventPublish> fetchByEventtype(String... values) {
        return fetch(AskResponseEventPublishTable.askResponseEventPublish.EVENTTYPE, values);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<AskResponseEventPublish> fetchByVersion(Integer... values) {
        return fetch(AskResponseEventPublishTable.askResponseEventPublish.VERSION, values);
    }
}
