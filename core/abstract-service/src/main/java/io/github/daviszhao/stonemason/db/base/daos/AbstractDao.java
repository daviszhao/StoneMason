package io.github.daviszhao.stonemason.db.base.daos;

import io.github.daviszhao.stonemason.api.base.PageData;
import org.jooq.*;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.DSL;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public abstract class AbstractDao<R extends UpdatableRecord<R>, E extends Serializable, K> extends DAOImpl<R, E, K> {
    protected AbstractDao(Table<R> table, Class<E> type) {
        super(table, type);
    }

    protected AbstractDao(Table<R> table, Class<E> type, Configuration configuration) {
        super(table, type, configuration);
    }


    private DSLContext context() {
        return DSL.using(configuration());
    }

    public PageData<E> queryPageData(List<Condition> conditions, final SortField<LocalDateTime> sortField, int pageSize, int page) {
        int totalItems = context().fetchCount(getTable(), DSL.condition(Operator.AND, conditions));
        if (totalItems == 0) return new PageData<>(0, 0, 1, Collections.emptyList());
        int totalPages = totalItems / pageSize;
        if (totalItems % pageSize > 0) totalPages += 1;
        assert page <= totalPages;
        List<E> users = context().selectFrom(getTable()).where(conditions).orderBy(sortField).offset((page - 1) + pageSize).limit(pageSize).fetchInto(getType());

        return new PageData<>(totalItems, totalPages, page, users);
    }

    public List<E> queryAllData(List<Condition> conditions, SortField<LocalDateTime> sortField) {
        SelectWhereStep<R> query = context().selectFrom(getTable());
        if (conditions.size() > 0) {
            query.where(conditions);
        }
        query.orderBy(sortField);
        return query.fetchInto(getType());
    }
}
