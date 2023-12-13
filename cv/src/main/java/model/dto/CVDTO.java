package model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.bean.BasicInfo;
import model.bean.CV;
import model.bean.Education;
import model.bean.Project;
import model.bean.Skill;
import model.dao.BasicInfoDAO;
import model.dao.EducationDAO;
import model.dao.ProjectDAO;
import model.dao.SkillDAO;
import model.dao.UserDAO;
import model.util.database.QueryType;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CVDTO {
    private UUID cvId;
    private UserDTO user;
    private List<BasicInfo> basicInfos;
    private List<Skill> skills;
    private List<Education> educations;
    private List<ProjectDTO> projects;

    static public CVDTO fromEntity(CV cv) {
        UserDAO userDAO = UserDAO.getInstance();
        BasicInfoDAO basicInfoDAO = BasicInfoDAO.getInstance();
        SkillDAO skillDAO = SkillDAO.getInstance();
        EducationDAO educationDAO = EducationDAO.getInstance();
        ProjectDAO projectDAO = ProjectDAO.getInstance();

        UserDTO userDTO = null;
        List<BasicInfo> basicInfos = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        List<Education> educations = new ArrayList<>();
        List<ProjectDTO> projects = new ArrayList<>();
        try {
            userDTO = UserDTO.fromEntity(userDAO.getById(cv.getUserId()).get());
            basicInfos = basicInfoDAO.makeQuery(QueryType.SELECT)
                    .where(String.format("cv_id='%s'", cv.getCvId().toString())).query().toEntityList();
            skills = skillDAO.makeQuery(QueryType.SELECT)
                    .where(String.format("cv_id='%s'", cv.getCvId().toString())).query().toEntityList();
            educations = educationDAO.makeQuery(QueryType.SELECT)
                    .where(String.format("cv_id='%s'", cv.getCvId().toString())).query().toEntityList();

            List<Project> projectEntities = projectDAO.makeQuery(QueryType.SELECT)
                    .where(String.format("cv_id='%s'", cv.getCvId().toString())).query().toEntityList();
            projectEntities.forEach((projEntity) -> projects.add(ProjectDTO.fromEntity(projEntity)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fromEntity(cv, userDTO, basicInfos, skills, educations, projects);
    }

    static public CVDTO fromEntity(CV cv, UserDTO user, List<BasicInfo> basicInfos, List<Skill> skills,
            List<Education> educations,
            List<ProjectDTO> projects) {
        return CVDTO.builder()
                .cvId(cv.getCvId())
                .user(user)
                .basicInfos(basicInfos)
                .skills(skills)
                .educations(educations)
                .projects(projects)
                .build();
    }

}
