package model.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.util.database.Entity;

public class Wife extends Entity<Integer> {
    private Integer id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String address;
    private boolean alive;

    public Wife() {}
    
    public Wife(int id, String name, String address, boolean alive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.alive = alive;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new HashMap<>();
        if(includePrimary)
            result.put("id", id.toString());
        result.put("name", wrapString(name));
        result.put("address", wrapString(address));
        result.put("alive", alive ? "1" : "0");
        return result;
    }

    @Override
    public Wife fromResultSet(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
            this.address = resultSet.getString("address");
            this.alive = resultSet.getBoolean("alive");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public Entry<String, Integer> getPrimaryValue() {
        return Map.entry("id", id);
    }
}
