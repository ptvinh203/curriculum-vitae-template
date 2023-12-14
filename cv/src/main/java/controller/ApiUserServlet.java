package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;
import model.dto.UserDTO;

@WebServlet(urlPatterns = "/api/user")
public class ApiUserServlet extends HttpServlet {
    UserBO userBO = UserBO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String email = req.getParameter("email");
        if(email != null) {
            UserDTO user = userBO.getByEmail(email);
            if(user == null)
                resp.getWriter().write("[]");
            List<UserDTO> users = new ArrayList<>();
            users.add(user);

            ObjectMapper objectMapper = new ObjectMapper();
            resp.getWriter().write(objectMapper.writeValueAsString(users));
        }
    }
}
