package model.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.util.database.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class ProjectDescription extends Entity<UUID> {
    private UUID descriptionId;
    private String description;
    private UUID cvId;

    public ProjectDescription(String description, UUID cvId) {
        this.descriptionId = UUID.randomUUID();
        this.description = description;
        this.cvId = cvId;
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new HashMap<>();
        if (includePrimary)
            result.put("description_id", wrapString(descriptionId.toString()));
        result.put("description", wrapString(description));
        result.put("cv_id", wrapString(cvId.toString()));
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.descriptionId = UUID.fromString(resultSet.getString("description_id"));
            this.description = resultSet.getString("description");
            this.cvId = UUID.fromString(resultSet.getString("cv_id"));
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        return Map.entry("description_id", descriptionId);
    }

}
