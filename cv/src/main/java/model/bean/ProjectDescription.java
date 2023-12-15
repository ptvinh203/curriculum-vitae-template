package model.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
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
    private UUID projectId;

    public ProjectDescription(String description, UUID projectId) {
        this.descriptionId = UUID.randomUUID();
        this.description = description;
        this.projectId = projectId;
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new LinkedHashMap<>();
        if (includePrimary)
            result.put("description_id", wrapString(descriptionId.toString()));
        result.put("description", wrapString(description));
        result.put("project_id", wrapString(projectId.toString()));
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.descriptionId = UUID.fromString(resultSet.getString("description_id"));
            this.description = resultSet.getString("description");
            this.projectId = UUID.fromString(resultSet.getString("project_id"));
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        if(descriptionId == null)
            return Map.entry("desccription_id", UUID.randomUUID());
        return Map.entry("description_id", descriptionId);
    }

}
