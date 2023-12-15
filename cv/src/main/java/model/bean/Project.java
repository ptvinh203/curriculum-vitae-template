package model.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
public class Project extends Entity<UUID> {
    private UUID projectId;
    private String projectName;
    private String time;
    private Integer numOfMember;
    private String github;
    private UUID cvId;

    public Project(String projectName, String time, int numOfMember, String github, UUID cvId) {
        this.projectId = UUID.randomUUID();
        this.projectName = projectName;
        this.time = time;
        this.numOfMember = numOfMember;
        this.github = github;
        this.cvId = cvId;
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new LinkedHashMap<>();
        if (includePrimary)
            result.put("project_id", wrapString(projectId.toString()));
        result.put("project_name", wrapString(projectName));
        result.put("time", wrapString(time));
        result.put("num_of_member", numOfMember.toString());
        result.put("github", wrapString(github));
        result.put("cv_id", wrapString(cvId.toString()));
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.projectId = UUID.fromString(resultSet.getString("project_id"));
            this.projectName = resultSet.getString("project_name");
            this.time = resultSet.getString("time");
            this.numOfMember = resultSet.getInt("num_of_member");
            this.github = resultSet.getString("github");
            this.cvId = UUID.fromString(resultSet.getString("cv_id"));
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        if(projectId == null)
            return Map.entry("project_id", UUID.randomUUID());
        return Map.entry("project_id", projectId);
    }

}
