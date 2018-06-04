/*
 * This file is generated by jOOQ.
 */
package io.github.daviszhao.stonemason.db.event.tables;


import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.db.event.Keys;
import io.github.daviszhao.stonemason.db.event.tables.records.NotifyEventPublishRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.EnumConverter;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.6"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class NotifyEventPublishTable extends TableImpl<NotifyEventPublishRecord> {

    /**
     * The reference instance of <code>user.t_notify_event_publish</code>
     */
    public static final NotifyEventPublishTable NOTIFY_EVENT_PUBLISH = new NotifyEventPublishTable();
    private static final long serialVersionUID = -1992440534;
    /**
     * The column <code>user.t_notify_event_publish.id</code>.
     */
    public final TableField<NotifyEventPublishRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>user.t_notify_event_publish.payload</code>.
     */
    public final TableField<NotifyEventPublishRecord, String> PAYLOAD = createField("payload", org.jooq.impl.SQLDataType.CLOB, this, "");
    /**
     * The column <code>user.t_notify_event_publish.status</code>. 'NEW','PROCESSED','IGNORE'
     */
    public final TableField<NotifyEventPublishRecord, ProcessStatus> STATUS = createField("status", SQLDataType.VARCHAR.length(20).nullable(false).defaultValue(DSL.inline("NEW", SQLDataType.VARCHAR)), this, "'NEW','PROCESSED','IGNORE'", new EnumConverter<>(String.class, ProcessStatus.class));
    /**
     * The column <code>user.t_notify_event_publish.eventId</code>.
     */
    public final TableField<NotifyEventPublishRecord, String> EVENTID = createField("eventId", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(true), this, "");
    /**
     * The column <code>user.t_notify_event_publish.version</code>.
     */
    public final TableField<NotifyEventPublishRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");
    /**
     * The column <code>user.t_notify_event_publish.eventType</code>.
     */
    public final TableField<NotifyEventPublishRecord, String> EVENTTYPE = createField("eventType", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");
    public final TableField<NotifyEventPublishRecord, LocalDateTime> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");
    public final TableField<NotifyEventPublishRecord, LocalDateTime> UPDATETIME = createField("updateTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * Create a <code>user.t_notify_event_publish</code> table reference
     */
    public NotifyEventPublishTable() {
        this("t_notify_event_publish", null);
    }

    /**
     * Create an aliased <code>user.t_notify_event_publish</code> table reference
     */
    public NotifyEventPublishTable(String alias) {
        this(alias, NOTIFY_EVENT_PUBLISH);
    }

    private NotifyEventPublishTable(String alias, Table<NotifyEventPublishRecord> aliased) {
        this(alias, aliased, null);
    }

    private NotifyEventPublishTable(String alias, Table<NotifyEventPublishRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this eventType
     */
    @Override
    public Class<NotifyEventPublishRecord> getRecordType() {
        return NotifyEventPublishRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<NotifyEventPublishRecord, Integer> getIdentity() {
        return Keys.IDENTITY_notifyEventPublish;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<NotifyEventPublishRecord> getPrimaryKey() {
        return Keys.KEY_T_NOTIFY_EVENT_PUBLISH_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<NotifyEventPublishRecord>> getKeys() {
        return Arrays.<UniqueKey<NotifyEventPublishRecord>>asList(Keys.KEY_T_NOTIFY_EVENT_PUBLISH_PRIMARY, Keys.KEY_T_NOTIFY_EVENT_PUBLISH_UK_NOTIFY__PUBLISH_EVENTID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishTable as(String alias) {
        return new NotifyEventPublishTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public NotifyEventPublishTable rename(String name) {
        return new NotifyEventPublishTable(name, null);
    }

    @Override
    public TableField<NotifyEventPublishRecord, ?> getRecordVersion() {
        return VERSION;
    }

    @Override
    public TableField<NotifyEventPublishRecord, ?> getRecordTimestamp() {
        return UPDATETIME;
    }
}
