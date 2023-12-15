package model.bo;

import java.util.NoSuchElementException;
import java.util.UUID;

import com.auth0.jwt.exceptions.JWTVerificationException;

import model.bean.BasicInfo;
import model.bean.CV;
import model.bean.Education;
import model.bean.Project;
import model.bean.ProjectDescription;
import model.bean.Skill;
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
                cvdto.setBasicInfo(new BasicInfo(
                    user.getFirstname() + " " + user.getLastname(), 
                    "", 
                    user.getEmail(),
                     "", 
                    "", 
                    "", 
                    null)
                );

            // Insert CV
            CV cv = cvdao.insert(new CV(cvdto.getCvName(), user.getUserid())).get();

            /*
             * Insert BasicInfo
             */
            BasicInfo basicInfo = cvdto.getBasicInfo();
            basicInfo.setBasicInfoId(UUID.randomUUID());
            basicInfo.setCvId(cv.getCvId());
            basicInfoDAO.insert(basicInfo).get();

            /*
             * Insert Education
             */
            if (cvdto.getEducations() != null && !cvdto.getEducations().isEmpty()) {
                cvdto.getEducations().forEach((education) -> {
                    education.setEducationId(UUID.randomUUID());
                    education.setCvId(cv.getCvId());
                    educationDAO.insert(education);
                });
            }

            /*
             * Insert Skill
             */
            if (cvdto.getSkills() != null && !cvdto.getSkills().isEmpty()) {
                cvdto.getSkills().forEach((skill) -> {
                    skill.setSkillId(UUID.randomUUID());
                    skill.setCvId(cv.getCvId());
                    skillDAO.insert(skill);
                });
            }

            /*
             * Insert Project
             */
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

            /*
             * Update BasicInfo
             */
            BasicInfo basicInfo = cvdto.getBasicInfo();
            BasicInfo entity = basicInfoDAO.getById(basicInfo.getBasicInfoId()).orElseThrow();
            entity.setName(basicInfo.getName());
            entity.setGithub(basicInfo.getGithub());
            entity.setEmail(basicInfo.getEmail());
            entity.setAddress(basicInfo.getAddress());
            entity.setPhone(basicInfo.getPhone());
            entity.setAboutMe(basicInfo.getAboutMe());
            basicInfo = basicInfoDAO.update(entity).get();

            /*
             * Update Education
             */
            if (cvdto.getEducations() != null && !cvdto.getEducations().isEmpty()) {
                cvdto.getEducations().forEach((education) -> {
                    try {
                        updateEducation(education);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            /*
             * Update Skill
             */
            if (cvdto.getSkills() != null && !cvdto.getSkills().isEmpty()) {
                cvdto.getSkills().forEach((skill) -> {
                    try {
                        updateSkill(skill);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            /*
             * Update Project
             */
            if (cvdto.getProjects() != null && !cvdto.getProjects().isEmpty()) {
                cvdto.getProjects().forEach((project) -> {
                    try {
                        updateProject(project);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    public Boolean deleteCV(String token, String cvId) {
        try {
            String userId = JwtUtil.extractSubject(token);
            CV cv = cvdao.getById(UUID.fromString(cvId)).orElseThrow();
            if (cv.getUserId().compareTo(UUID.fromString(userId)) != 0) {
                throw new Exception("You don't have perssion to access!");
            }

            cvdao.deleteById(cv.getCvId());
            return true;
        } catch (JWTVerificationException e) {
            System.out.println("===> JWTVerificationException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean deleteSkill(String token, String cvId, String skillId) {
        try {
            String userId = JwtUtil.extractSubject(token);
            CV cv = cvdao.getById(UUID.fromString(cvId)).orElseThrow();
            if (cv.getUserId().compareTo(UUID.fromString(userId)) != 0) {
                throw new Exception("You don't have perssion to access!");
            }

            skillDAO.deleteById(UUID.fromString(skillId));
            return true;
        } catch (JWTVerificationException e) {
            System.out.println("===> JWTVerificationException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean deleteEducation(String token, String cvId, String educationId) {
        try {
            String userId = JwtUtil.extractSubject(token);
            CV cv = cvdao.getById(UUID.fromString(cvId)).orElseThrow();
            if (cv.getUserId().compareTo(UUID.fromString(userId)) != 0) {
                throw new Exception("You don't have perssion to access!");
            }

            educationDAO.deleteById(UUID.fromString(educationId));
            return true;
        } catch (JWTVerificationException e) {
            System.out.println("===> JWTVerificationException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Boolean deleteProject(String token, String cvId, String projectId) {
        try {
            String userId = JwtUtil.extractSubject(token);
            CV cv = cvdao.getById(UUID.fromString(cvId)).orElseThrow();
            if (cv.getUserId().compareTo(UUID.fromString(userId)) != 0) {
                throw new Exception("You don't have perssion to access!");
            }

            projectDAO.deleteById(UUID.fromString(projectId));
            return true;
        } catch (JWTVerificationException e) {
            System.out.println("===> JWTVerificationException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /*
     * Internal Function
     */
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
                    projectDescription.setDescription(description.getDescription());
                    projectDescriptionDAO.update(description);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return ProjectDTO.fromEntity(project);
    }

    private Education updateEducation(Education education) throws NoSuchElementException, Exception {
        Education entity = educationDAO.getById(education.getEducationId()).orElseThrow();
        entity.setEducationName(education.getEducationName());
        entity.setTime(education.getTime());
        education = educationDAO.update(entity).get();
        return education;
    }

    private Skill updateSkill(Skill skill) throws NoSuchElementException, Exception {
        Skill entity = skillDAO.getById(skill.getSkillId()).orElseThrow();
        entity.setSkillName(skill.getSkillName());
        skill = skillDAO.update(entity).get();
        return skill;
    }
}
