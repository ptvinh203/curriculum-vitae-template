package model.util.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import at.favre.lib.crypto.bcrypt.BCrypt.Verifyer;

public class PasswordUtil {
    private final int HASH_ROUND = 10; 
    private Hasher hasher = BCrypt.withDefaults();
    private Verifyer verifyer = BCrypt.verifyer();

    static private PasswordUtil instance;
    static public PasswordUtil getInstance() {
        if(instance == null)
            instance = new PasswordUtil();
        return instance;
    }

    private PasswordUtil() {}

    public String getHash(String password) {
        return hasher.hashToString(HASH_ROUND, password.toCharArray());
    }

    public boolean verify(String password, String hash) {
        return verifyer.verify(password.toCharArray(), hash.toCharArray()).verified;
    }
}
