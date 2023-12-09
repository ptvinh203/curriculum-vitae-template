package model.bo;

import java.util.List;

import model.dao.WifeDAO;
import model.dto.WifeDTO;

public class WifeBO {
    static private WifeBO instance;
    static public WifeBO getInstance() {
        if(instance == null)
            instance = new WifeBO(WifeDAO.getInstance());
        return instance;
    }
    private final WifeDAO wifeDAO;
    public WifeBO(WifeDAO wifeDAO) {
        this.wifeDAO = wifeDAO;
    }

    public List<WifeDTO> getAllWifes() throws Exception {
        return (List<WifeDTO>) wifeDAO.getAll().stream().map((wife) -> WifeDTO.fromBean(wife)).toList();
    }
}
