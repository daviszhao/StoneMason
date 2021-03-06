/*
 * This file is generated by jOOQ.
 */
package io.github.daviszhao.stonemason.db.event.tables;


import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.db.event.Keys;
import io.github.daviszhao.stonemason.db.event.tables.records.AskResponseEventPublishRecord;
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
public class AskResponseEventPublishTable extends TableImpl<AskResponseEventPublishRecord> {

    /**
     * The reference instance of <code>user.t_ask_response_event_publish</code>
     */
    public static final AskResponseEventPublishTable ASK_RESPONSE_EVENT_PUBLISH = new AskResponseEventPublishTable();
    private static final long serialVersionUID = -1345931121;
    /**
     * The column <code>user.t_ask_response_event_publish.id</code>.
     */
    public final TableField<AskResponseEventPublishRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>user.t_ask_response_event_publish.payload</code>.
     */
    public final TableField<AskResponseEventPublishRecord, String> PAYLOAD = createField("payload", org.jooq.impl.SQLDataType.CLOB, this, "");
    /**
     * The column <code>user.t_ask_response_event_publish.status</code>. 'NEW','PROCESSED','IGNORE'
     */
    public final TableField<AskResponseEventPublishRecord, ProcessStatus> STATUS = createField("status", SQLDataType.VARCHAR.length(20).nullable(false).defaultValue(DSL.inline("NEW", SQLDataType.VARCHAR)), this, "'NEW','PROCESSED','IGNORE'", new EnumConverter<>(String.class, ProcessStatus.class));
    /**
     * The column <code>user.t_ask_response_event_publish.eventId</code>.
     */
    public final TableField<AskResponseEventPublishRecord, String> EVENTID = createField("eventId", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");
    /**
     * The column <code>user.t_ask_response_event_publish.success</code>.
     */
    public final TableField<AskResponseEventPublishRecord, Boolean> SUCCESS = createField("success", org.jooq.impl.SQLDataType.BIT.defaultValue(org.jooq.impl.DSL.inline("b'0'", org.jooq.impl.SQLDataType.BIT)), this, "");
    /**
     * The column <code>user.t_ask_response_event_publish.askEventId</code>.
     */
    public final TableField<AskResponseEventPublishRecord, String> ASKEVENTID = createField("askEventId", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");
    /**
     * The column <code>user.t_ask_response_event_publish.eventType</code>.
     */
    public final TableField<AskResponseEventPublishRecord, String> EVENTTYPE = createField("eventType", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");
    /**
     * The column <code>user.t_ask_response_event_publish.version</code>.
     */
    public final TableField<AskResponseEventPublishRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");
    public final TableField<AskResponseEventPublishRecord, LocalDateTime> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");
    public final TableField<AskResponseEventPublishRecord, LocalDateTime> UPDATETIME = createField("updateTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * Create a <code>user.t_ask_response_event_publish</code> table reference
     */
    public AskResponseEventPublishTable() {
        this("t_ask_response_event_publish", null);
    }

    /**
     * Create an aliased <code>user.t_ask_response_event_publish</code> table reference
     */
    public AskResponseEventPublishTable(String alias) {
        this(alias, ASK_RESPONSE_EVENT_PUBLISH);
    }

    private AskResponseEventPublishTable(String alias, Table<AskResponseEventPublishRecord> aliased) {
        this(alias, aliased, null);
    }

    private AskResponseEventPublishTable(String alias, Table<AskResponseEventPublishRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this eventType
     */
    @Override
    public Class<AskResponseEventPublishRecord> getRecordType() {
        return AskResponseEventPublishRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AskResponseEventPublishRecord, Integer> getIdentity() {
        return Keys.IDENTITY_askResponseEventPublish;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AskResponseEventPublishRecord> getPrimaryKey() {
        return Keys.KEY_T_ASK_RESPONSE_EVENT_PUBLISH_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AskResponseEventPublishRecord>> getKeys() {
        return Arrays.<UniqueKey<AskResponseEventPublishRecord>>asList(Keys.KEY_T_ASK_RESPONSE_EVENT_PUBLISH_PRIMARY, Keys.KEY_T_ASK_RESPONSE_EVENT_PUBLISH_UK_ASKRESP__PUBLISH_EVENTID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AskResponseEventPublishTable as(String alias) {
        return new AskResponseEventPublishTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AskResponseEventPublishTable rename(String name) {
        return new AskResponseEventPublishTable(name, null);
    }

    @Override
    public TableField<AskResponseEventPublishRecord, ?> getRecordVersion() {
        return VERSION;
    }

    @Override
    public TableField<AskResponseEventPublishRecord, ?> getRecordTimestamp() {
        return UPDATETIME;
    }
}
