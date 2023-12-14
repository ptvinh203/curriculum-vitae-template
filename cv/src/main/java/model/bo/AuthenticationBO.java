package model.bo;

import com.auth0.jwt.exceptions.JWTVerificationException;

import model.bean.LoginData;
import model.dao.LoginDataDAO;
import model.dto.UserDTO;
import model.util.security.JwtUtil;
import model.util.security.PasswordUtil;

public class AuthenticationBO {
    static private AuthenticationBO instance;

    static public AuthenticationBO getInstance() {
        if (instance == null)
            instance = new AuthenticationBO();
        return instance;
    }

    private LoginDataDAO loginDataDAO = LoginDataDAO.getInstance();
    private UserBO userBO = UserBO.getInstance();

    public String login(String email, String password) {
        try {
            LoginData loginData = loginDataDAO.login(email, password);
            if (loginData == null)
                return null;

            return JwtUtil.generateToken(loginData.getUserId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String register(UserDTO userDTO, String password) {
        try {
            UserDTO userResponse = userBO.insert(userDTO);
            if (userResponse == null)
                return null;

            loginDataDAO.insert(new LoginData(userResponse.getUserid(), PasswordUtil.getInstance().getHash(password)));

            return JwtUtil.generateToken(userResponse.getUserid().toString());
        } catch (JWTVerificationException e) {
            System.out.println("===> JWTVerificationException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
