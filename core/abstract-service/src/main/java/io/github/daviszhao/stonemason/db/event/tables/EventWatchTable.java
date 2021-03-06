/*
 * This file is generated by jOOQ.
 */
package io.github.daviszhao.stonemason.db.event.tables;


import io.github.daviszhao.stonemason.busEvent.constants.AskEventStatus;
import io.github.daviszhao.stonemason.busEvent.payloads.ExtraParams;
import io.github.daviszhao.stonemason.db.base.utils.JsonObjectsConverter;
import io.github.daviszhao.stonemason.db.event.Keys;
import io.github.daviszhao.stonemason.db.event.tables.records.EventWatchRecord;
import org.jooq.*;
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
public class EventWatchTable extends TableImpl<EventWatchRecord> {

    /**
     * The reference instance of <code>user.t_event_watch</code>
     */
    public static final EventWatchTable EVENT_WATCH = new EventWatchTable();
    private static final long serialVersionUID = 329891240;
    /**
     * The column <code>user.t_event_watch.id</code>.
     */
    public final TableField<EventWatchRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>user.t_event_watch.extraParams</code>.
     */
    public final TableField<EventWatchRecord, ExtraParams> EXTRAPARAMS = createField("extraParams", org.jooq.impl.SQLDataType.CLOB, this, "", new JsonObjectsConverter<>(ExtraParams.class));

    /**
     * The column <code>user.t_event_watch.askEventIds</code>.
     */
    public final TableField<EventWatchRecord, String> ASKEVENTIDS = createField("askEventIds", SQLDataType.VARCHAR.length(255), this, "");
    /**
     * The column <code>user.t_event_watch.callbackClass</code>.
     */
    public final TableField<EventWatchRecord, String> CALLBACKCLASS = createField("callbackClass", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");
    /**
     * The column <code>user.t_event_watch.united</code>.
     */
    public final TableField<EventWatchRecord, Boolean> UNITED = createField("united", org.jooq.impl.SQLDataType.BIT.nullable(false), this, "");
    /**
     * The column <code>user.t_event_watch.askEventStatus</code>. 'PENDING','TIMEOUT','FAILED','SUCCESS','CANCELLED'
     */
    public final TableField<EventWatchRecord, AskEventStatus> ASKEVENTSTATUS = createField("askEventStatus", SQLDataType.VARCHAR.length(20), this, "'PENDING','TIMEOUT','FAILED','SUCCESS','CANCELLED'", new EnumConverter<>(String.class, AskEventStatus.class));
    /**
     * The column <code>user.t_event_watch.timeoutTime</code>.
     */
    public final TableField<EventWatchRecord, LocalDateTime> TIMEOUTTIME = createField("timeoutTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");
    public final TableField<EventWatchRecord, LocalDateTime> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");
    public final TableField<EventWatchRecord, LocalDateTime> UPDATETIME = createField("updateTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");
    /**
     * The column <code>user.t_event_watch.version</code>.
     */
    public final TableField<EventWatchRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");


    /**
     * Create a <code>user.t_event_watch</code> table reference
     */
    public EventWatchTable() {
        this("t_event_watch", null);
    }

    /**
     * Create an aliased <code>user.t_event_watch</code> table reference
     */
    public EventWatchTable(String alias) {
        this(alias, EVENT_WATCH);
    }

    private EventWatchTable(String alias, Table<EventWatchRecord> aliased) {
        this(alias, aliased, null);
    }

    private EventWatchTable(String alias, Table<EventWatchRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this eventType
     */
    @Override
    public Class<EventWatchRecord> getRecordType() {
        return EventWatchRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<EventWatchRecord, Integer> getIdentity() {
        return Keys.IDENTITY_eventWatch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<EventWatchRecord> getPrimaryKey() {
        return Keys.KEY_T_EVENT_WATCH_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<EventWatchRecord>> getKeys() {
        return Arrays.<UniqueKey<EventWatchRecord>>asList(Keys.KEY_T_EVENT_WATCH_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EventWatchTable as(String alias) {
        return new EventWatchTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public EventWatchTable rename(String name) {
        return new EventWatchTable(name, null);
    }

    @Override
    public TableField<EventWatchRecord, ?> getRecordVersion() {
        return VERSION;
    }

    @Override
    public TableField<EventWatchRecord, ?> getRecordTimestamp() {
        return UPDATETIME;
    }
}
