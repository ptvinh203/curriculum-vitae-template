package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.WifeBO;
import model.dao.WifeDAO;
import model.dto.WifeDTO;
import model.util.database.QueryType;

@WebServlet(urlPatterns = "/wife")
public class WifeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WifeBO wifeBO = WifeBO.getInstance();
        List<WifeDTO> wifes;
        try {
            wifes = wifeBO.getAllWifes();
            req.setAttribute("wifes", wifes);
            req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
