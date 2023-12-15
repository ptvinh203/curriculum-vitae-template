package controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.AuthenticationBO;
import model.util.security.JwtUtil;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    AuthenticationBO authenticationBO = AuthenticationBO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {    
            Cookie authCookie = getTokenCookie(cookies);
            if(authCookie != null && JwtUtil.verifyToken(authCookie.getValue())) {
                System.out.println(authCookie.getValue());
                // redirect to ...
                return;
            }
        }
        req.getRequestDispatcher("login.html").forward(req, resp);
    }

    private Cookie getTokenCookie(Cookie[] cookies) {
        for(int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName() == "token")
                return cookies[i];
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");  
        String password = req.getParameter("password");
        
        String jwttoken = authenticationBO.login(email, password);
        if(jwttoken == null) {
            resp.sendRedirect("login?message=Incorrect email or password");
        }
        Cookie cookie = new Cookie("token", jwttoken);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        resp.addCookie(cookie);
    }
}
