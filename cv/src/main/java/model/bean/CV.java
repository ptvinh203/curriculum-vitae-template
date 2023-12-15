package model.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class CV extends Entity<UUID> {

    private UUID cvId;
    private UUID userId;

    public CV(UUID userId) {
        this(
            UUID.randomUUID(),
            userId
        );
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new LinkedHashMap<>();
        if(includePrimary)
            result.put("cv_id", wrapString(cvId.toString()));
        result.put("user_id", wrapString(userId.toString()));

        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.cvId = UUID.fromString(resultSet.getString("cv_id"));
            this.userId = UUID.fromString(resultSet.getString("user_id"));
            return this;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        if(cvId == null)
            return Map.entry("cv_id", UUID.randomUUID());
        return Map.entry("cv_id", cvId);
    }
}
