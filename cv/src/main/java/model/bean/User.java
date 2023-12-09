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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User extends Entity<UUID> {

    private UUID userid;
    private String username;
    private String email;
    private String firstname;
    private String lastname;

    private UUID roleid;

    public User(String username, String email, String firstname, String lastname, UUID roleid) {
        this(
            UUID.randomUUID(),
            username,
            email,
            firstname,
            lastname,
            roleid
        );
    }

    @Override
    public Map<String, String> toValueMap(boolean includePrimary) {
        Map<String, String> result = new HashMap<>();
        if(includePrimary)
            result.put("UserID", wrapString(userid.toString()));
        result.put("username", wrapString(username));
        result.put("firstname", wrapString(firstname));
        result.put("lastname", wrapString(lastname));
        result.put("roleid", wrapString(roleid.toString()));
        return result;
    }

    @Override
    public Entity<UUID> fromResultSet(ResultSet resultSet) {
        try {
            this.userid = UUID.fromString(resultSet.getString("UserID"));
            this.username = resultSet.getString("username");
            this.firstname = resultSet.getString("firstname");
            this.lastname = resultSet.getString("lastname");
            this.roleid = UUID.fromString(resultSet.getString("roleid"));
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Entry<String, UUID> getPrimaryValue() {
        return Map.entry("UserID", userid);
    }
    
}
