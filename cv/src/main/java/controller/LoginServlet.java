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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    AuthenticationBO authenticationBO = AuthenticationBO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");  
        String password = req.getParameter("password");
        
        String jwttoken = authenticationBO.login(email, password);
        Cookie cookie = new Cookie("token", jwttoken);
        resp.addCookie(cookie);
    }
}
