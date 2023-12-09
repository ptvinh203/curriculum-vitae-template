package model.bo;

import model.dao.AuthDAO;

public class AuthBO {
    static private AuthBO instance;
    static public AuthBO getInstance() {
        if(instance == null)
            instance = new AuthBO(AuthDAO.getInstance());
        return instance;
    }
    private final AuthDAO authDAO;

    public AuthBO(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    public boolean login(String username, String password) {
        return authDAO.login(username, password);
    }
}
