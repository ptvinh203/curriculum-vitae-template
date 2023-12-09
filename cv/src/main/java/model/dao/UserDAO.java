package model.dao;

import java.util.UUID;

import model.bean.User;
import model.util.database.BaseDAO;


public class UserDAO extends BaseDAO<User, UUID> {
    public UserDAO() {
        super("User", User.class);
    }   

    static private UserDAO instance;
    static public UserDAO getInstance() {
        if(instance == null)
            instance = new UserDAO();
        return instance;
    }
}
