package model.dao;

import java.util.UUID;

import model.bean.UserRole;
import model.util.database.BaseDAO;

public class UserRoleDAO extends BaseDAO<UserRole, UUID> {
    public UserRoleDAO() {
        super("UserRole", UserRole.class);
    }
}
