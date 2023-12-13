package model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.bean.Project;
import model.bean.ProjectDescription;
import model.dao.ProjectDescriptionDAO;
import model.util.database.QueryType;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProjectDTO {
    private UUID projectId;
    private String projectName;
    private String time;
    private Integer numOfMember;
    private String github;
    private UUID cvId;
    private List<ProjectDescription> descriptions;

    static public ProjectDTO fromEntity(Project entity) {
        ProjectDescriptionDAO projDesDAO = ProjectDescriptionDAO.getInstance();

        List<ProjectDescription> descriptions = new ArrayList<>();
        try {
            descriptions = projDesDAO.makeQuery(QueryType.SELECT)
                    .where(String.format("project_id='%s'", entity.getProjectId().toString())).query()
                    .toEntityList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fromEntity(entity, descriptions);
    }

    static public ProjectDTO fromEntity(Project entity, List<ProjectDescription> descriptions) {
        return ProjectDTO.builder()
                .projectId(entity.getProjectId())
                .projectName(entity.getProjectName())
                .time(entity.getTime())
                .numOfMember(entity.getNumOfMember())
                .cvId(entity.getCvId())
                .github(entity.getGithub())
                .descriptions(descriptions)
                .build();
    }
}
