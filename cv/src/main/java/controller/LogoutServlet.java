package controller;

import java.io.IOException;

import controller.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie authCookie = CookieUtil.getCookie(req, "token");
        if(authCookie != null) {
            authCookie.setMaxAge(0);
            authCookie.setValue("");
    
            resp.addCookie(authCookie);
        }
        resp.sendRedirect(".");
    }
}
