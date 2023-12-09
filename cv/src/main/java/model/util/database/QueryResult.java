package model.util.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryResult<T extends Entity<?>> {
    private Optional<ResultSet> result;
    private Class<T> baseClass;

    public Optional<ResultSet> getResultSet() {
        return result;
    }

    public QueryResult(Optional<ResultSet> result, Class<T> baseClass) {
        this.result = result;
        this.baseClass = baseClass;
    }

    public List<T> toEntityList() {
        if(result.isEmpty())
            return new ArrayList<>();
        ResultSet resultSet = result.get();
        try {
            ArrayList<T> entities = new ArrayList<>();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                try {
                    T entity = baseClass.getConstructor().newInstance();
                    entity.fromResultSet(resultSet);
                    entities.add(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Optional<T> first() {
        if(result.isEmpty())
            return Optional.empty();
        ResultSet resultSet = result.get();
        try {
            if(resultSet.first()) {
                return (Optional<T>) Optional.of(baseClass.getConstructor().newInstance().fromResultSet(resultSet));
            }
            return Optional.empty();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
