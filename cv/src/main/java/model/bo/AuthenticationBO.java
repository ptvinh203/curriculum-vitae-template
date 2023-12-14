package model.bo;

import model.bean.LoginData;
import model.dao.LoginDataDAO;
import model.dto.UserDTO;

public class AuthenticationBO {
    LoginDataDAO loginDataDAO = LoginDataDAO.getInstance();

    public UserDTO login(String email, String password) {
        LoginData loginData = loginDataDAO.login(email, password);
        if(loginData == null)
            return null;
        return UserBO.getInstance().getById(loginData.getUserId());
    }
}
