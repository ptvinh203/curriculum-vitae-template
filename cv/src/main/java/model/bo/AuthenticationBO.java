package model.bo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import model.bean.LoginData;
import model.dao.LoginDataDAO;

public class AuthenticationBO {
    static private AuthenticationBO instance;
    static public AuthenticationBO getInstance() {
        if(instance == null)
            instance = new AuthenticationBO();
        return instance;
    }

    private LoginDataDAO loginDataDAO = LoginDataDAO.getInstance();

    public String login(String email, String password) {
        LoginData loginData = loginDataDAO.login(email, password);
        if(loginData == null)
            return null;

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJiZmE3NmU0Zi1kMjMzLTQwZjAtYTBjMi0xMjRmOWYwYmIwM2YifQ.K-WgT90CA8R-tEmXpDeKFyXAT7_jAfe_x6ES3ZI8k8Y";

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("DMVppp")).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            // System.out.println(decodedJWT.getSubject());
            return decodedJWT.getSubject();
        } catch(JWTVerificationException e) {
            e.printStackTrace();;
        }
        return "a";
    //     return JWT.create()
    //         .withSubject(loginData.getUserId().toString())
    //         .sign(Algorithm.HMAC256("DMV"));
    }
}
