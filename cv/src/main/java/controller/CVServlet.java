package controller;

import java.io.IOException;
import java.util.UUID;

import controller.util.CookieUtil;
import controller.util.UserSessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
        if (uuidStr == null) {
            resp.sendRedirect("home");
            return;
        }
        UUID cvid = UUID.fromString(req.getParameter("cvid"));
        CVDTO cv = cvbo.getById(cvid);
        if (cv == null) {
            resp.sendRedirect("home");
            return;
        }
        req.setAttribute("cv", cv);
        if (mode != null) {
            UserSessionUtil.ensureUser(req);
            UserDTO user = (UserDTO) req.getSession().getAttribute("current_user");
            switch (mode.toLowerCase()) {
                case "edit":
                    if (user == null || !user.getUserid().equals(cv.getUser().getUserid())) {
                        resp.sendRedirect(String.format("?cvid=%s", uuidStr));
                        return;
                    }
                    req.getRequestDispatcher("cv-edit.jsp").forward(req, resp);
                    return;
                case "delete":
                    if (user == null || !user.getUserid().equals(cv.getUser().getUserid())) {
                        resp.sendRedirect(String.format("?cvid=%s", uuidStr));
                        return;
                    }
                    System.out.println("====> CHECK DELETE CV: " +
                            cvbo.deleteCV(CookieUtil.getCookie(req, "token").getValue(), cv.getCvId().toString()));
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                    return;
                default:
                    break;
            }
        }
        req.getRequestDispatcher("cv-view.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!UserSessionUtil.ensureUser(req)) {
            resp.sendRedirect("/cv/login");
            return;
        }
        String cvId = req.getParameter("cvid");
        if(cvId == null) {
            resp.sendRedirect("/cv/home");
            return;
        }

        CVDTO cv = cvbo.getById(UUID.fromString(cvId));
        UserDTO user = (UserDTO) req.getSession().getAttribute("current_user");

        if(!user.getUserid().equals(cv.getUser().getUserid())) {
            resp.sendRedirect("/cv/home");
            return;
        }

        String cvName = req.getParameter("cvName");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String aboutMe = req.getParameter("aboutMe");
        String githubLink = req.getParameter("githubLink");
    }
}
