package model.dao;

import java.util.UUID;

import model.bean.Project;
import model.util.database.BaseDAO;

public class ProjectDAO extends BaseDAO<Project, UUID> {

    public ProjectDAO() {
        super("project", Project.class);
    }

}
