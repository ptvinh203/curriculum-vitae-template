package model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Builder
public class CVDTO {
        private UUID cvId;
        private String cvName = "";
        private UserDTO user;
        private BasicInfo basicInfo = null;
        private List<Skill> skills = new ArrayList<>();
        private List<Education> educations = new ArrayList<>();
        private List<ProjectDTO> projects = new ArrayList<>();

        static public CVDTO fromEntity(CV cv) {
                UserDAO userDAO = UserDAO.getInstance();
                UserDTO userDTO = null;
                try {
                        userDTO = UserDTO.fromEntity(userDAO.getById(cv.getUserId()).get());
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return fromEntity(cv, userDTO);
        }

        static public CVDTO fromEntity(CV cv, UserDTO user, BasicInfo basicInfo, List<Skill> skills,
                        List<Education> educations,
                        List<ProjectDTO> projects) {
                return CVDTO.builder()
                                .cvId(cv.getCvId())
                                .cvName(cv.getCvName())
                                .user(user)
                                .basicInfo(basicInfo)
                                .skills(skills)
                                .educations(educations)
                                .projects(projects)
                                .build();
        }

        static public CVDTO fromEntity(CV cv, UserDTO user) {
            BasicInfoDAO basicInfoDAO = BasicInfoDAO.getInstance();
            SkillDAO skillDAO = SkillDAO.getInstance();
            EducationDAO educationDAO = EducationDAO.getInstance();
            ProjectDAO projectDAO = ProjectDAO.getInstance();

            BasicInfo basicInfo = new BasicInfo();
            List<Skill> skills = new ArrayList<>();
            List<Education> educations = new ArrayList<>();
            List<ProjectDTO> projects = new ArrayList<>();

            try {
                basicInfo = basicInfoDAO.makeQuery(QueryType.SELECT)
                                .where(String.format("cv_id='%s'", cv.getCvId().toString())).query().first()
                                .orElse(null);

                skills = skillDAO.makeQuery(QueryType.SELECT)
                                .where(String.format("cv_id='%s'", cv.getCvId().toString())).query()
                                .toEntityList();
                educations = educationDAO.makeQuery(QueryType.SELECT)
                                .where(String.format("cv_id='%s'", cv.getCvId().toString())).query()
                                .toEntityList();

                List<Project> projectEntities = projectDAO.makeQuery(QueryType.SELECT)
                                .where(String.format("cv_id='%s'", cv.getCvId().toString())).query()
                                .toEntityList();
                projectEntities.forEach((projEntity) -> projects.add(ProjectDTO.fromEntity(projEntity)));
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return fromEntity(cv, user, basicInfo, skills, educations, projects);
        }

}
