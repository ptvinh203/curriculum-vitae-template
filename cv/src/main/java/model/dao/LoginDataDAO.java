package model.dao;

import java.util.UUID;

import model.bean.LoginData;
import model.util.database.BaseDAO;

public class LoginDataDAO extends BaseDAO<LoginData, UUID> {
    public LoginDataDAO() {
        super("LoginData", LoginData.class);
    } 

    static private LoginDataDAO instance;
    static public LoginDataDAO getInstance() {
        if(instance == null)
            instance = new LoginDataDAO();
        return instance;
    }
}
