package model.dao;

import java.util.UUID;

import model.bean.BasicInfo;
import model.util.database.BaseDAO;

public class BasicInfoDAO extends BaseDAO<BasicInfo, UUID>{
    public BasicInfoDAO() {
        super("BasicInfo", BasicInfo.class);
    }
}
