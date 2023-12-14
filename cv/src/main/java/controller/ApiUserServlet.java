package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        String userId = req.getParameter("user-id");
        String email = req.getParameter("email");
        if(userId != null) {
            UserDTO user = userBO.getById(UUID.fromString(userId));
            if(user == null) {
                resp.getWriter().write("[]");
                return;
            }
            List<UserDTO> users = new ArrayList<>();
            users.add(user);

            ObjectMapper objectMapper = new ObjectMapper();
            resp.getWriter().write(objectMapper.writeValueAsString(users));
        }
        else if(email != null) {
            UserDTO user = userBO.getByEmail(email);
            if(user == null) {
                resp.getWriter().write("[]");
                return;
            }
            List<UserDTO> users = new ArrayList<>();
            users.add(user);

            ObjectMapper objectMapper = new ObjectMapper();
            resp.getWriter().write(objectMapper.writeValueAsString(users));
        }
        else {
            String username = req.getParameter("username");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");

            List<String> filters = new ArrayList<>();
            if(username != null)
                filters.add(String.format("username LIKE '%%s%'", username));
            if(firstname != null)
                filters.add(String.format("firstname LIKE '%%s%'", firstname));
            if(lastname != null)
                filters.add(String.format("lastname LIKE '%%s%'", lastname));
            
            List<UserDTO> users = userBO.findBy(filters);
            ObjectMapper objectMapper = new ObjectMapper();
            resp.getWriter().write(objectMapper.writeValueAsString(users));
        }
    }
}
