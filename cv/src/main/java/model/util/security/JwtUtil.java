package model.util.security;

import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
    private JwtUtil() {
    };

    private static final String privateKey = "DMV";
    private static final Algorithm algorithm = Algorithm.HMAC256(privateKey);

    public static String generateToken(String userId) {
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withSubject(userId)
                .sign(algorithm);
    }

    public static String extractSubject(String token) throws JWTVerificationException, IllegalArgumentException {
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getSubject();
    }

    public static Boolean verifyToken(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
