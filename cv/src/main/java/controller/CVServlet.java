package controller;

import java.io.IOException;
import java.util.UUID;

import controller.util.CookieUtil;
import controller.util.UserSessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.CVBO;
import model.dto.CVDTO;
import model.dto.UserDTO;

@WebServlet(urlPatterns = "/cv")
public class CVServlet extends HttpServlet {

    private CVBO cvbo = CVBO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = req.getParameter("cvid");
        String mode = req.getParameter("mode");
        if(uuidStr == null) {
            resp.sendRedirect("home");
            return;
        }
        UUID cvid = UUID.fromString(req.getParameter("cvid"));
        CVDTO cv = cvbo.getById(cvid);
        if(cv == null) {
            resp.sendRedirect("home");
            return;
        }
        req.setAttribute("cv", cv);
        if(mode == null || mode != "edit") {
            req.getRequestDispatcher("cv-view.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
