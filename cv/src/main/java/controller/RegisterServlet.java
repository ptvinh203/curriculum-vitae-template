package controller;

import java.io.IOException;

import controller.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.AuthenticationBO;
import model.dto.UserDTO;
import model.util.security.JwtUtil;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    AuthenticationBO authenticationBO = AuthenticationBO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie authCookie = CookieUtil.getCookie(req, "token");

        if(authCookie != null && JwtUtil.verifyToken(authCookie.getValue())) {
            System.out.println(authCookie.getValue());
            resp.sendRedirect("home");
            return;
        }
        
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");

        UserDTO user = UserDTO.builder()
            .email(email)
            .username(username)
            .firstname(firstname)
            .lastname(lastname)
            .build();

        String jwtToken = authenticationBO.register(user, password);
        if(jwtToken == null) {
            resp.sendRedirect("register?message=Something went wrong while registering");
            return;
        }
        Cookie cookie = new Cookie("token", jwtToken);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        resp.addCookie(cookie);
        resp.sendRedirect("home");
    }
}
