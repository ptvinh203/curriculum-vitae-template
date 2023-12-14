package model.bo;

import java.util.NoSuchElementException;
import java.util.UUID;

import com.auth0.jwt.exceptions.JWTVerificationException;

import model.bean.BasicInfo;
import model.bean.CV;
import model.bean.Project;
import model.bean.ProjectDescription;
import model.bean.User;
import model.dao.BasicInfoDAO;
import model.dao.CVDAO;
import model.dao.EducationDAO;
import model.dao.ProjectDAO;
import model.dao.ProjectDescriptionDAO;
import model.dao.SkillDAO;
import model.dao.UserDAO;
import model.dto.CVDTO;
import model.dto.ProjectDTO;
import model.util.security.JwtUtil;

public class CVBO {
    static private CVBO instance;

    static public CVBO getInstance() {
        if (instance == null)
            instance = new CVBO();
        return instance;
    }

    private UserDAO userDAO = UserDAO.getInstance();
    private CVDAO cvdao = CVDAO.getInstance();
    private BasicInfoDAO basicInfoDAO = BasicInfoDAO.getInstance();
    private SkillDAO skillDAO = SkillDAO.getInstance();
    private ProjectDAO projectDAO = ProjectDAO.getInstance();
    private ProjectDescriptionDAO projectDescriptionDAO = ProjectDescriptionDAO.getInstance();
    private EducationDAO educationDAO = EducationDAO.getInstance();

    public CVDTO createCV(String token, CVDTO cvdto) {
        try {
            String userId = JwtUtil.extractSubject(token);
            User user = userDAO.getById(UUID.fromString(userId)).orElseThrow();
            if (cvdto.getBasicInfo() == null)
                return null;

            CV cv = cvdao.insert(new CV(user.getUserid())).get();
            BasicInfo basicInfo = cvdto.getBasicInfo();
            basicInfo.setBasicInfoId(UUID.randomUUID());
            basicInfo.setCvId(cv.getCvId());
            basicInfoDAO.insert(basicInfo).get();

            if (cvdto.getEducations() != null && !cvdto.getEducations().isEmpty()) {
                cvdto.getEducations().forEach((education) -> {
                    education.setEducationId(UUID.randomUUID());
                    education.setCvId(cv.getCvId());
                    educationDAO.insert(education);
                });
            }
            if (cvdto.getSkills() != null && !cvdto.getSkills().isEmpty()) {
                cvdto.getSkills().forEach((skill) -> {
                    skill.setSkillId(UUID.randomUUID());
                    skill.setCvId(cv.getCvId());
                    skillDAO.insert(skill);
                });
            }
            if (cvdto.getProjects() != null && !cvdto.getProjects().isEmpty()) {
                cvdto.getProjects().forEach((project) -> {
                    insertProject(cv.getCvId(), project);
                });
            }

            return CVDTO.fromEntity(cv);
        } catch (JWTVerificationException e) {
            System.out.println("===> JWTVerificationException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CVDTO updateCV(String token, String cvId, CVDTO cvdto) {
        try {
            String userId = JwtUtil.extractSubject(token);
            CV cv = cvdao.getById(UUID.fromString(cvId)).orElseThrow();
            if (cv.getUserId().compareTo(UUID.fromString(userId)) != 0) {
                throw new Exception("You don't have perssion to access!");
            }

            if (cvdto.getBasicInfo() == null)
                return null;

            BasicInfo basicInfo = basicInfoDAO.getById(cvdto.getCvId()).orElseThrow();

            if (cvdto.getEducations() != null && !cvdto.getEducations().isEmpty()) {
                cvdto.getEducations().forEach((education) -> {
                    educationDAO.update(education);
                });
            }
            if (cvdto.getSkills() != null && !cvdto.getSkills().isEmpty()) {
                cvdto.getSkills().forEach((skill) -> {
                    skillDAO.update(skill);
                });
            }
            if (cvdto.getProjects() != null && !cvdto.getProjects().isEmpty()) {
                cvdto.getProjects().forEach((project) -> {
                    insertProject(cv.getCvId(), project);
                });
            }

            return CVDTO.fromEntity(cv);

        } catch (JWTVerificationException e) {
            System.out.println("===> JWTVerificationException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ProjectDTO insertProject(UUID cvId, ProjectDTO projectDTO) {
        Project project = projectDAO
                .insert(new Project(projectDTO.getProjectName(), projectDTO.getTime(), projectDTO.getNumOfMember(),
                        projectDTO.getGithub(), cvId))
                .get();

        if (projectDTO.getDescriptions() != null && !projectDTO.getDescriptions().isEmpty()) {
            projectDTO.getDescriptions().forEach(description -> {
                description.setDescriptionId(UUID.randomUUID());
                description.setProjectId(project.getProjectId());
                projectDescriptionDAO.insert(description);
            });
        }
        return ProjectDTO.fromEntity(project);
    }

    private ProjectDTO updateProject(ProjectDTO projectDTO) throws NoSuchElementException, Exception {
        Project project = projectDAO.getById(projectDTO.getProjectId()).orElseThrow();
        project.setProjectName(projectDTO.getProjectName());
        project.setTime(projectDTO.getTime());
        project.setNumOfMember(projectDTO.getNumOfMember());
        project.setGithub(projectDTO.getGithub());
        project = projectDAO.update(project).get();

        if (projectDTO.getDescriptions() != null && !projectDTO.getDescriptions().isEmpty()) {
            projectDTO.getDescriptions().forEach(description -> {
                try {
                    ProjectDescription projectDescription = projectDescriptionDAO
                            .getById(description.getDescriptionId()).get();
                } catch (Exception e) {

                }
            });
        }
        return ProjectDTO.fromEntity(project);
    }
}
