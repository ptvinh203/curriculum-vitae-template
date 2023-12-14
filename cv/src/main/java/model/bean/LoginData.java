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
public class LoginData extends Entity<UUID> {

    private UUID loginDataId;
    private UUID userId;
    private String passwordHash;

    public LoginData(UUID userId, String passwordHash) {
        this(
            UUID.randomUUID(),
            userId,
            passwordHash
        );
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new HashMap<>();
        if(includePrimary)
            result.put("login_data_id", wrapString(loginDataId.toString()));
        result.put("user_id", wrapString(userId.toString()));
        result.put("password_hash", wrapString(passwordHash));
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.loginDataId = UUID.fromString(resultSet.getString("login_data_id"));
            this.userId = UUID.fromString(resultSet.getString("user_id"));
            this.passwordHash = resultSet.getString("password_hash");
            return this;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        if(loginDataId == null)
            return Map.entry("login_data_id", UUID.randomUUID());
        return Map.entry("login_data_id", loginDataId);
    }
}
