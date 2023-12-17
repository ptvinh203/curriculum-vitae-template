package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import controller.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.CVBO;
import model.dto.CVDTO;
import model.dto.ProjectDTO;

@WebServlet(urlPatterns = "/api/project")
public class ApiProjectServlet extends HttpServlet {
    private CVBO cvbo = CVBO.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cvidStr = req.getParameter("cvid");
        UUID cvid = UUID.fromString(cvidStr);
        String projectName = req.getParameter("projectName");
        String projectTime = req.getParameter("projectTime");
        int projectNMembers = Integer.parseInt(req.getParameter("projectNMembers"));
        String projectGithub = req.getParameter("projectGithub");
        
        CVDTO cv = cvbo.getById(cvid);
        if(cv == null) {
            resp.sendError(400, "CV with id = " + cvidStr + " not found!");
            return;
        }

        cv.getProjects().add(
            ProjectDTO.builder()
                .projectName(projectName)
                .time(projectTime)
                .numOfMember(projectNMembers)
                .github(projectGithub)
                .descriptions(new ArrayList<>())
                .build()   
        );

        cvbo.updateCV(CookieUtil.getCookie(req, "token").getValue(), cvidStr, cv);
        resp.sendRedirect("/cv/cv?cvid=" + cvidStr + "&mode=edit");
    }
}
