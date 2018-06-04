/*
 * This file is generated by jOOQ.
 */
package io.github.daviszhao.stonemason.db.event.tables.records;


import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.db.event.tables.NotifyEventPublishTable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.6"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class NotifyEventPublishRecord extends UpdatableRecordImpl<NotifyEventPublishRecord> implements Record8<Integer, String, ProcessStatus, String, Integer, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = -1319174120;

    /**
     * Create a detached NotifyEventPublishRecord
     */
    public NotifyEventPublishRecord() {
        super(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH);
    }

    /**
     * Getter for <code>user.t_notify_event_publish.id</code>.
     */
    @NotNull
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Create a detached, initialised NotifyEventPublishRecord
     */
    public NotifyEventPublishRecord(Integer id, String payload, ProcessStatus status, String eventid, Integer version, String eventtype, LocalDateTime createtime, LocalDateTime updatetime) {
        super(NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH);

        set(0, id);
        set(1, payload);
        set(2, status);
        set(3, eventid);
        set(4, version);
        set(5, eventtype);
        set(6, createtime);
        set(7, updatetime);
    }

    /**
     * Setter for <code>user.t_notify_event_publish.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>user.t_notify_event_publish.payload</code>.
     */
    @Size(max = 65535)
    public String getPayload() {
        return (String) get(1);
    }

    /**
     * Getter for <code>user.t_notify_event_publish.status</code>. 'NEW','PROCESSED','IGNORE'
     */
    @Size(max = 20)
    public ProcessStatus getStatus() {
        return (ProcessStatus) get(2);
    }

    /**
     * Setter for <code>user.t_notify_event_publish.payload</code>.
     */
    public void setPayload(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>user.t_notify_event_publish.eventId</code>.
     */
    @Size(max = 50)
    public String getEventid() {
        return (String) get(3);
    }

    /**
     * Setter for <code>user.t_notify_event_publish.version</code>.
     */
    public void setVersion(Integer value) {
        set(4, value);
    }

    /**
     * Setter for <code>user.t_notify_event_publish.status</code>. 'NEW','PROCESSED','IGNORE'
     */
    public void setStatus(ProcessStatus value) {
        set(2, value);
    }

    /**
     * Setter for <code>user.t_notify_event_publish.eventId</code>.
     */
    public void setEventid(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>user.t_notify_event_publish.eventType</code>.
     */
    @NotNull
    @Size(max = 50)
    public String getEventtype() {
        return (String) get(5);
    }

    /**
     * Getter for <code>user.t_notify_event_publish.version</code>.
     */
    public Integer getVersion() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>user.t_notify_event_publish.eventType</code>.
     */
    public void setEventtype(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>user.t_notify_event_publish.createTime</code>.
     */
    public LocalDateTime getCreatetime() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>user.t_notify_event_publish.createTime</code>.
     */
    public void setCreatetime(LocalDateTime value) {
        set(6, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 eventType implementation
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>user.t_notify_event_publish.updateTime</code>.
     */
    public LocalDateTime getUpdatetime() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>user.t_notify_event_publish.updateTime</code>.
     */
    public void setUpdatetime(LocalDateTime value) {
        set(7, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, ProcessStatus, String, Integer, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, ProcessStatus, String, Integer, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.PAYLOAD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ProcessStatus> field3() {
        return NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.EVENTID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.EVENTTYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field7() {
        return NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.CREATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessStatus value3() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getEventid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getEventtype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field8() {
        return NotifyEventPublishTable.NOTIFY_EVENT_PUBLISH.UPDATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getPayload();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value7() {
        return getCreatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value8() {
        return getUpdatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord value2(String value) {
        setPayload(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord value3(ProcessStatus value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord value4(String value) {
        setEventid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord value5(Integer value) {
        setVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord value6(String value) {
        setEventtype(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord value7(LocalDateTime value) {
        setCreatetime(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord value8(LocalDateTime value) {
        setUpdatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotifyEventPublishRecord values(Integer value1, String value2, ProcessStatus value3, String value4, Integer value5, String value6, LocalDateTime value7, LocalDateTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }
}
