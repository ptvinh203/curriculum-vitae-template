package model.dao;

import java.util.UUID;

import model.bean.CV;
import model.util.database.BaseDAO;

public class CVDAO extends BaseDAO<CV, UUID> {
    public CVDAO() {
        super("CV", CV.class);
    }
}
