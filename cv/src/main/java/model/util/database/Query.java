package model.util.database;
import java.util.ArrayList;

public class Query<T extends Entity<?>> {
    public QueryType type;
    public String tableName;

    private DBUtil dbUtil;
    private String queryString;
    private ArrayList<String> conditions = new ArrayList<>();
    private ArrayList<T> entities = new ArrayList<>();
    private Class<T> baseClass;

    public Query(String tableName, QueryType type, DBUtil dbUtil, Class<T> baseClass) throws Exception {
        this.tableName = tableName;
        this.type = type;
        this.dbUtil = dbUtil;
        this.baseClass = baseClass;
        
        switch (type) {
            case SELECT:
                queryString = String.format("SELECT * FROM `%s`", tableName);
                break;
            case INSERT:
                queryString = String.format("INSERT INTO `%s` VALUES ", tableName);
                break;
            case UPDATE:
                queryString = String.format("UPDATE `%s` SET ", tableName);
                break;
            case DELETE:
                queryString = String.format("DELETE FROM `%s`", tableName);
                break;
            default:
                throw new Exception("Invalid QueryType " + type.toString());
        }   
    }

    public Query<T> where(String condition) throws Exception {
        if (type == QueryType.INSERT)
            throw new Exception("QueryType INSERT cannot have a WHERE clause");
        conditions.add(condition);
        return this;
    }

    public Query<T> value(T value) throws Exception {
        if (type == QueryType.UPDATE && entities.size() > 0)
            throw new Exception("QueryType UPDATE cannot have more than one value");
        entities.add(value);
        return this;
    }

    public String makeQueryString() throws Exception {
        String finalQueryString = queryString;
        switch (type) {
            case INSERT:
                String valuesString = String.join(
                    ",",
                    entities.stream().map(e -> {
                        return String.format("(%s)", String.join(
                                ",", 
                                e.toValueMap().entrySet().stream().map(entry -> entry.getValue()).toList()
                            )
                        );
                    }).toList()
                );
                System.out.println("Adding: " + valuesString);
                finalQueryString += valuesString;
                break;
            case UPDATE:
                if(entities.size() < 1)
                    throw new Exception("QueryType UPDATE must have atleast one value");
                T entity = entities.get(0);
                String valueString = String.join(
                    ",", 
                    entity.toValueMap(false).entrySet().stream().map(
                        entry -> String.format("%s=%s", entry.getKey(), entry.getValue())
                    ).toList()
                );
                finalQueryString += valueString;
                break;
            default:
                break;
        }
        if(conditions.size() > 0) {
            finalQueryString += String.format(" WHERE %s", String.join(" AND ", conditions));
        }
        return finalQueryString;
    }

    public QueryResult<T> query() throws Exception {
        return new QueryResult<>(dbUtil.execute(this), baseClass);
    }
}
