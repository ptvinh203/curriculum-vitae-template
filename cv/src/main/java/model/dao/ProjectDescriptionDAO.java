package model.dao;

import java.util.UUID;

import model.bean.ProjectDescription;
import model.util.database.BaseDAO;

public class ProjectDescriptionDAO extends BaseDAO<ProjectDescription, UUID> {

    public ProjectDescriptionDAO() {
        super("projectdescription", ProjectDescription.class);
    }

}
