package model.dao;


import model.bean.Wife;
import model.util.database.BaseDAO;

public class WifeDAO extends BaseDAO<Wife, Integer> {
    static private WifeDAO instance;
    static public WifeDAO getInstance() {
        if(instance == null)
            instance = new WifeDAO();
        return instance;
    }
    public WifeDAO() {
        super("Wife", Wife.class);
    }
}