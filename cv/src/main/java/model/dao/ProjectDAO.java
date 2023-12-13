package model.dao;

import java.util.UUID;

import model.bean.Project;
import model.util.database.BaseDAO;

public class ProjectDAO extends BaseDAO<Project, UUID> {

    public ProjectDAO() {
        super("Project", Project.class);
    }

    static private ProjectDAO instance;

    static public ProjectDAO getInstance() {
        if (instance == null)
            instance = new ProjectDAO();
        return instance;
    }

}
