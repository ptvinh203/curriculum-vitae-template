package controller;

import java.io.IOException;

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

@WebServlet(urlPatterns = "/cv/create")
public class CreateCVServlet extends HttpServlet {
    private CVBO cvbo = CVBO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!UserSessionUtil.ensureUser(req)) {
            resp.sendRedirect(".");
            return;
        }

        // Creating empty cv
        Cookie authCookie = CookieUtil.getCookie(req, "token");
        
        UserDTO user = (UserDTO) req.getSession().getAttribute("current_user");
        CVDTO cv = new CVDTO();
        cv.setUser(user);
        cv = cvbo.createCV(authCookie.getValue(), cv);

        resp.sendRedirect("/cv/cv?cvid="+cv.getCvId().toString());
    }
}
