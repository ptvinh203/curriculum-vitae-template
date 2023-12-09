package model.dao;

import java.util.UUID;

import model.bean.Skill;
import model.util.database.BaseDAO;

public class SkillDAO extends BaseDAO<Skill, UUID> {

    public SkillDAO() {
        super("skill", Skill.class);
    }

}
