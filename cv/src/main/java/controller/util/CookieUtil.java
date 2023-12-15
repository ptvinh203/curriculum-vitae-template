package controller.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil {
    static public Cookie[] getCookies(HttpServletRequest req) {
        return req.getCookies();
    }

    static public Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies)
                if(cookie.getName().equals(name))
                    return cookie;
        }
        return null;
    }
}
