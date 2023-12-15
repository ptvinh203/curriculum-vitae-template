package model.dao;

import java.util.UUID;

import model.bean.UserRole;
import model.util.database.BaseDAO;

public class UserRoleDAO extends BaseDAO<UserRole, UUID> {
    private UserRoleDAO() {
        super("UserRole", UserRole.class);
    }
    static private UserRoleDAO instance;
    static public UserRoleDAO getInstance() {
        if(instance == null)
            instance = new UserRoleDAO();
        return instance;
    }

    public UUID defaultRoleId() {
        return UUID.fromString("bd127c42-9afa-11ee-b9d1-0242ac120002");
    }
}
