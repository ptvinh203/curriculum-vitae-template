package model.util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryResult<T extends Entity<?>> {
    private Connection connection;
    private Optional<ResultSet> result = Optional.empty();
    private Class<T> baseClass;

    public Optional<ResultSet> getResultSet() {
        return result;
    }

    public QueryResult(Connection connection, Class<T> baseClass) {
        this.connection = connection;
        this.baseClass = baseClass;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setResultSet(ResultSet resultSet) {
        this.result = Optional.of(resultSet);
    }

    public List<T> toEntityList() {
        if(result.isEmpty()) {
            DBUtil.getInstance().closeConnection(connection);
            return new ArrayList<>();
        }
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
        } finally {
            DBUtil.getInstance().closeResultSet(resultSet);
            DBUtil.getInstance().closeConnection(connection);
        }
        return new ArrayList<>();
    }

    public Optional<T> first() {
        if(result.isEmpty()) {
            DBUtil.getInstance().closeConnection(connection);
            return Optional.empty();
        }
        ResultSet resultSet = result.get();
        try {
            if(resultSet.first()) {
                return (Optional<T>) Optional.of(baseClass.getConstructor().newInstance().fromResultSet(resultSet));
            }
            return Optional.empty();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.getInstance().closeResultSet(resultSet);
            DBUtil.getInstance().closeConnection(connection);
        }
        return Optional.empty();
    }
}
