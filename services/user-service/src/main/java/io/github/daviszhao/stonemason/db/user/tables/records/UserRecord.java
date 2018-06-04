
package io.github.daviszhao.stonemason.db.user.tables.records;


import io.github.daviszhao.stonemason.db.user.tables.UserTable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


/**
 * 用户表
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.6"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record7<Integer, String, String, String, Boolean, LocalDateTime, Integer> {

    private static final long serialVersionUID = -319566704;

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(UserTable.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Integer id, String username, String password, String salt, Boolean locked, LocalDateTime createtime, Integer version) {
        super(UserTable.USER);

        set(0, id);
        set(1, username);
        set(2, password);
        set(3, salt);
        set(4, locked);
        set(5, createtime);
        set(6, version);
    }

    /**
     * Getter for <code>user.t_user.ID</code>.
     */
    @NotNull
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>user.t_user.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>user.t_user.USERNAME</code>. 用户名
     */
    @NotNull
    @Size(max = 50)
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>user.t_user.USERNAME</code>. 用户名
     */
    public void setUsername(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>user.t_user.PASSWORD</code>. 密码
     */
    @NotNull
    @Size(max = 50)
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>user.t_user.PASSWORD</code>. 密码
     */
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>user.t_user.SALT</code>. 加密盐
     */
    @NotNull
    @Size(max = 100)
    public String getSalt() {
        return (String) get(3);
    }

    /**
     * Setter for <code>user.t_user.SALT</code>. 加密盐
     */
    public void setSalt(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>user.t_user.LOCKED</code>. 是否锁定
     */
    public Boolean getLocked() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>user.t_user.LOCKED</code>. 是否锁定
     */
    public void setLocked(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>user.t_user.CREATETIME</code>.
     */
    @NotNull
    public LocalDateTime getCreatetime() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>user.t_user.CREATETIME</code>.
     */
    public void setCreatetime(LocalDateTime value) {
        set(5, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>user.t_user.VERSION</code>.
     */
    public Integer getVersion() {
        return (Integer) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 eventType implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>user.t_user.VERSION</code>.
     */
    public void setVersion(Integer value) {
        set(6, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, String, Boolean, LocalDateTime, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, String, Boolean, LocalDateTime, Integer> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return UserTable.USER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return UserTable.USER.USERNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return UserTable.USER.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return UserTable.USER.SALT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field5() {
        return UserTable.USER.LOCKED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field6() {
        return UserTable.USER.CREATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return UserTable.USER.VERSION;
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
    public String value2() {
        return getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getSalt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value5() {
        return getLocked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value6() {
        return getCreatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value2(String value) {
        setUsername(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value3(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value4(String value) {
        setSalt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value5(Boolean value) {
        setLocked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value6(LocalDateTime value) {
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
    public UserRecord value7(Integer value) {
        setVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord values(Integer value1, String value2, String value3, String value4, Boolean value5, LocalDateTime value6, Integer value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }
}
