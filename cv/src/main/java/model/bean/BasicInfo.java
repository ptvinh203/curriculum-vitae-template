package model.bean;

import java.sql.ResultSet;
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
        if(includePrimary)
            result.put("cv_id", wrapString(cvId.toString()));
        
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromResultSet'");
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrimaryValue'");
    }
}
