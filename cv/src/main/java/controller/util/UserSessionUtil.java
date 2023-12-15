package controller.util;

import java.util.UUID;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.bo.UserBO;
import model.dto.UserDTO;
import model.util.security.JwtUtil;

public class UserSessionUtil {
    static private UserBO userBO = UserBO.getInstance();

    static public boolean ensureUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Cookie authCookie = CookieUtil.getCookie(req, "token");
            if(authCookie == null) {
                session.setAttribute("current_user", null);
                return false;
            }
        if(!JwtUtil.verifyToken(authCookie.getValue())) {
            session.setAttribute("current_user", null);
            return false;
        }
        String uuidStr = JwtUtil.extractSubject(authCookie.getValue());
        UserDTO user = (UserDTO) session.getAttribute("current_user");
        if(user == null || !user.getUserid().toString().equals(uuidStr)) {
            try {
                user = userBO.getById(UUID.fromString(uuidStr));
                session.setAttribute("current_user", user);
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
