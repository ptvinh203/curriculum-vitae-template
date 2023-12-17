package controller;


import java.io.IOException;
import java.util.UUID;

import controller.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Skill;
import model.bo.CVBO;
import model.dto.CVDTO;

@WebServlet(urlPatterns = "/api/skill")
public class ApiSkillServlet extends HttpServlet {
    CVBO cvbo = CVBO.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cvidStr = req.getParameter("cvid");
        UUID cvid = UUID.fromString(cvidStr);
        String skillName = req.getParameter("skillName");
        
        CVDTO cv = cvbo.getById(cvid);
        if(cv == null) {
            resp.sendError(400, "CV with id = " + cvidStr + " not found!");
            return;
        }
        cv.getSkills().add(new Skill(skillName, cvid));

        cvbo.updateCV(CookieUtil.getCookie(req, "token").getValue(), cvidStr, cv);
        resp.sendRedirect("/cv/cv?cvid=" + cvidStr + "&mode=edit");
    }
}
