package model.dao;

import java.util.UUID;

import model.bean.Education;
import model.util.database.BaseDAO;

public class EducationDAO extends BaseDAO<Education, UUID> {

    public EducationDAO() {
        super("Education", Education.class);
    }

    static private EducationDAO instance;

    static public EducationDAO getInstance() {
        if (instance == null)
            instance = new EducationDAO();
        return instance;
    }
}
