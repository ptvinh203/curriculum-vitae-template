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
    private String roleName;
    private Integer permission;

    public UserRole(String roleName, int permission) {
        this(
            UUID.randomUUID(),
            roleName,
            permission
        );
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new HashMap<>();
        if(includePrimary)
            result.put("role_id", wrapString(roleId.toString()));
        result.put("role_name", wrapString(roleName));
        result.put("permission", permission.toString());
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.roleId = UUID.fromString(resultSet.getString("role_id"));
            this.roleName = resultSet.getString("role_name");
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