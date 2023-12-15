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
public class Education extends Entity<UUID> {
    private UUID educationId;
    private String educationName;
    private String time;
    private UUID cvId;

    public Education(String educationName, String time, UUID cvId) {
        this.educationId = UUID.randomUUID();
        this.educationName = educationName;
        this.time = time;
        this.cvId = cvId;
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new LinkedHashMap<>();
        if (includePrimary)
            result.put("education_id", wrapString(educationId.toString()));

        result.put("education_name", wrapString(educationName));
        result.put("time", wrapString(time));
        result.put("cv_id", wrapString(cvId.toString()));
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.educationId = UUID.fromString(resultSet.getString("education_id"));
            this.educationName = resultSet.getString("education_name");
            this.time = resultSet.getString("time");
            this.cvId = UUID.fromString(resultSet.getString("cv_id"));
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        if(educationId == null)
            return Map.entry("education_id", UUID.randomUUID());
        return Map.entry("education_id", educationId);
    }

}
