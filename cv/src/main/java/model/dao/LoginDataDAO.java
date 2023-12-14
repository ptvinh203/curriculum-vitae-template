package model.dao;

import java.util.Optional;
import java.util.UUID;

import model.bean.LoginData;
import model.bean.User;
import model.util.database.BaseDAO;
import model.util.database.QueryType;
import model.util.security.PasswordUtil;

public class LoginDataDAO extends BaseDAO<LoginData, UUID> {
    public LoginDataDAO() {
        super("LoginData", LoginData.class);
    }

    static private LoginDataDAO instance;

    static public LoginDataDAO getInstance() {
        if (instance == null)
            instance = new LoginDataDAO();
        return instance;
    }

    public LoginData login(String email, String password) {
        PasswordUtil passwordUtil = PasswordUtil.getInstance();
        try {
            Optional<User> user = UserDAO.getInstance().makeQuery(QueryType.SELECT)
                    .where(String.format("email='%s'", email))
                    .query()
                    .first();
            if (user.isEmpty())
                return null;
            Optional<LoginData> loginData = this.makeQuery(QueryType.SELECT)
                    .where(String.format("user_id='%s'", user.get().getUserid().toString()))
                    .query()
                    .first();
            if (loginData.isEmpty())
                return null;
            if (passwordUtil.verify(password, loginData.get().getPasswordHash()))
                return loginData.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
