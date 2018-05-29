/*
 * This file is generated by jOOQ.
 */
package io.github.daviszhao.stonemason.db.user.tables;


import io.github.daviszhao.stonemason.db.user.Keys;
import io.github.daviszhao.stonemason.db.user.User;
import io.github.daviszhao.stonemason.db.user.tables.records.UserRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


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
public class UserTable extends TableImpl<UserRecord> {

    /**
     * The reference instance of <code>user.t_user</code>
     */
    public static final UserTable table = new UserTable();
    private static final long serialVersionUID = 2005297338;
    /**
     * The column <code>user.t_user.ID</code>.
     */
    public final TableField<UserRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");
    /**
     * The column <code>user.t_user.USERNAME</code>. 用户名
     */
    public final TableField<UserRecord, String> USERNAME = createField("USERNAME", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "用户名");
    /**
     * The column <code>user.t_user.PASSWORD</code>. 密码
     */
    public final TableField<UserRecord, String> PASSWORD = createField("PASSWORD", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "密码");
    /**
     * The column <code>user.t_user.SALT</code>. 加密盐
     */
    public final TableField<UserRecord, String> SALT = createField("SALT", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "加密盐");
    /**
     * The column <code>user.t_user.LOCKED</code>. 是否锁定
     */
    public final TableField<UserRecord, Boolean> LOCKED = createField("LOCKED", org.jooq.impl.SQLDataType.BIT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("b'0'", org.jooq.impl.SQLDataType.BIT)), this, "是否锁定");
    /**
     * The column <code>user.t_user.CREATETIME</code>.
     */
    public final TableField<UserRecord, LocalDateTime> CREATETIME = createField("CREATETIME", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");
    /**
     * The column <code>user.t_user.VERSION</code>.
     */
    public final TableField<UserRecord, Integer> VERSION = createField("VERSION", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>user.t_user</code> table reference
     */
    public UserTable() {
        this("t_user", null);
    }

    /**
     * Create an aliased <code>user.t_user</code> table reference
     */
    public UserTable(String alias) {
        this(alias, table);
    }

    private UserTable(String alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserTable(String alias, Table<UserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "用户表");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return User.USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserRecord, Integer> getIdentity() {
        return Keys.IDENTITY_table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.KEY_T_USER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserRecord>> getKeys() {
        return Arrays.<UniqueKey<UserRecord>>asList(Keys.KEY_T_USER_PRIMARY, Keys.KEY_T_USER_UK_USER_USERNAME, Keys.KEY_T_USER_UK_USER_SALT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableField<UserRecord, Integer> getRecordVersion() {
        return VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserTable as(String alias) {
        return new UserTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserTable rename(String name) {
        return new UserTable(name, null);
    }
}
