package model.dao;

import java.util.UUID;

import model.bean.ProjectDescription;
import model.util.database.BaseDAO;

public class ProjectDescriptionDAO extends BaseDAO<ProjectDescription, UUID> {

    public ProjectDescriptionDAO() {
        super("ProjectDescription", ProjectDescription.class);
    }

    static private ProjectDescriptionDAO instance;

    static public ProjectDescriptionDAO getInstance() {
        if (instance == null)
            instance = new ProjectDescriptionDAO();
        return instance;
    }

}
