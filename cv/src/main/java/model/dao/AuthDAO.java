package model.dao;

import model.util.database.DBUtil;

public class AuthDAO {
    static private AuthDAO instance;
    static public AuthDAO getInstance() {
        if(instance == null)
            instance = new AuthDAO();
        return instance;
    }

    private AuthDAO() {
        dbUtil.getConnection();
    }
    
    DBUtil dbUtil = DBUtil.getInstance();

    public boolean login(String username, String password) {
        return true;
    }
}
