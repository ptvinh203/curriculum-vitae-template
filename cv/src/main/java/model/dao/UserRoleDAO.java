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
        if (instance == null)
            instance = new UserRoleDAO();
        return instance;
    }

    public UUID defaultRoleId() {
        return UUID.fromString("6db18eb2-9bd4-4c93-944c-364581c1c975");
    }
}
