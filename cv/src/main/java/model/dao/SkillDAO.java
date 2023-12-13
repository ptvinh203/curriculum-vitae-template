package model.dao;

import java.util.UUID;

import model.bean.Skill;
import model.util.database.BaseDAO;

public class SkillDAO extends BaseDAO<Skill, UUID> {

    public SkillDAO() {
        super("Skill", Skill.class);
    }

    static private SkillDAO instance;

    static public SkillDAO getInstance() {
        if (instance == null)
            instance = new SkillDAO();
        return instance;
    }

}
