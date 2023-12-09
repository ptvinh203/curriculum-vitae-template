package model.util.database;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Entity<U> {
    public Map<String, String> toValueMap() {
        return toValueMap(true);
    }
    public abstract Map<String, String> toValueMap(boolean includePrimary);
    public abstract Entity<U> fromResultSet(ResultSet resultSet);
    public abstract Entry<String, U> getPrimaryValue();
    protected String wrapString(String value) {
        return String.format("'%s'", value);
    }
}
