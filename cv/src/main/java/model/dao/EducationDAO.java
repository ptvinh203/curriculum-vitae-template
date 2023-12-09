package model.dao;

import java.util.UUID;

import model.bean.Education;
import model.util.database.BaseDAO;

public class EducationDAO extends BaseDAO<Education, UUID> {

    public EducationDAO() {
        super("education", Education.class);
    }

}
