package model.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import model.util.database.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole extends Entity<UUID> {

    private UUID roleId;
    private String rolename;
    private Integer permission;

    public UserRole(String rolename, int permission) {
        this(
            UUID.randomUUID(),
            rolename,
            permission
        );
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new HashMap<>();
        if(includePrimary)
            result.put("role_id", wrapString(roleId.toString()));
        result.put("rolename", wrapString(rolename));
        result.put("permission", permission.toString());
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.roleId = UUID.fromString(resultSet.getString("role_id"));
            this.rolename = resultSet.getString("rolename");
            this.permission = resultSet.getInt("permission");
            return this;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        return Map.entry("role_id", roleId);
    }
}