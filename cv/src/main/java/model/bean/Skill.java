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
@Getter
@Setter
@Builder

public class Skill extends Entity<UUID> {
    private UUID skillId;
    private String skillName;
    private UUID cvId;

    public Skill(String skillName, UUID cvId) {
        this.skillId = UUID.randomUUID();
        this.skillName = skillName;
        this.cvId = cvId;
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new LinkedHashMap<>();
        if (includePrimary)
            result.put("skill_id", wrapString(skillId.toString()));
        result.put("skill_name", wrapString(skillName));
        result.put("cv_id", wrapString(cvId.toString()));
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.skillId = UUID.fromString(resultSet.getString("skill_id"));
            this.skillName = resultSet.getString("skill_name");
            this.cvId = UUID.fromString(resultSet.getString("cv_id"));
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        if(skillId == null)
            return Map.entry("skill_id", UUID.randomUUID());
        return Map.entry("skill_id", skillId);
    }

}
