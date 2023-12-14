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
public class BasicInfo extends Entity<UUID> {

    private UUID basicInfoId;
    private String name;
    private String github;
    private String email;
    private String address;
    private String phone;
    private String aboutMe;
    private UUID cvId;

    public BasicInfo(String name, String github, String email, String address, String phone, String aboutMe,
            UUID cvId) {
        this.basicInfoId = UUID.randomUUID();
        this.name = name;
        this.github = github;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.aboutMe = aboutMe;
        this.cvId = cvId;
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new HashMap<>();
        if (includePrimary)
            result.put("basic_info_id", wrapString(basicInfoId.toString()));

        result.put("name", wrapString(name));
        result.put("github", wrapString(github));
        result.put("email", wrapString(email));
        result.put("address", wrapString(address));
        result.put("phone", wrapString(phone));
        result.put("about_me", wrapString(aboutMe));
        result.put("cv_id", wrapString(cvId.toString()));
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.basicInfoId = UUID.fromString(resultSet.getString("basic_info_id"));
            this.name = resultSet.getString("name");
            this.github = resultSet.getString("github");
            this.email = resultSet.getString("email");
            this.address = resultSet.getString("address");
            this.phone = resultSet.getString("phone");
            this.aboutMe = resultSet.getString("about_me");
            this.cvId = UUID.fromString(resultSet.getString("cv_id"));
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        if(basicInfoId == null)
            return Map.entry("basic_info_id", UUID.randomUUID());
        return Map.entry("basic_info_id", basicInfoId);
    }
}
