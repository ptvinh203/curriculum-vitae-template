package model.util.database;

import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;

public class BaseDAO<T extends Entity<U>, U> {

    public String tableName;
    private Class<T> baseClass;
    private T baseEntity;

    public BaseDAO(String tableName, Class<T> baseClass) {
        this.tableName = tableName;
        this.baseClass = baseClass;
        try {
            this.baseEntity = baseClass.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> getAll() throws Exception {
        return new Query<T>(tableName, QueryType.SELECT, DBUtil.getInstance(), baseClass)
            .query()
            .toEntityList();
    }

    public Optional<T> getById(U id) throws Exception {
        Entry<String, U> primaryEntry = this.baseEntity.getPrimaryValue();
        String condition;
        if (id instanceof Number) {
            condition = String.format("%s=%s", primaryEntry.getKey(), id);
        } else {
            condition = String.format("%s='%s'", primaryEntry.getKey().toString(), id);
        }

        return new Query<T>(tableName, QueryType.SELECT, DBUtil.getInstance(), baseClass)
            .where(condition)
            .query()
            .first();
    }

    public Optional<T> insert(T entity) {
        try {
            new Query<>(tableName, QueryType.INSERT, DBUtil.getInstance(), baseClass)
                .value(entity)
                .query();
            return Optional.of(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<T> update(T entity) {
        try {
            new Query<>(tableName, QueryType.UPDATE, DBUtil.getInstance(), baseClass)
                .value(entity)
                .query();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(T entity) {
        deleteById((U) entity.getPrimaryValue().getValue());
    }

    public void deleteById(U id) {
        Entry<String, U> primaryEntry = this.baseEntity.getPrimaryValue();
        String condition;
        if (id instanceof Number) {
            condition = String.format("%s=%s", primaryEntry.getKey(), id);
        } else {
            condition = String.format("%s='%s'", primaryEntry.getKey().toString(), id);
        }
        try {
            new Query<>(tableName, QueryType.DELETE, DBUtil.getInstance(), baseClass)
                .where(condition)
                .query();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Query<T> makeQuery(QueryType queryType) throws Exception {
        return new Query<>(tableName, queryType, DBUtil.getInstance(), baseClass);
    }
}
