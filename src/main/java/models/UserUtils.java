package models;

import web.PasswordAuth;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static repositories.DbConnection.userDB;

public class UserUtils {
    public User retrieveUserFromRequest(HttpServletRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isPremium = Boolean.parseBoolean(request.getParameter("isPremium"));

        PasswordAuth passwordAuth = new PasswordAuth();
        password = passwordAuth.hashPassword(password);

        return new User(username, password, isPremium);
    }

    public int checkUsername (String username){
        for (int i = 0; i < userDB.size(); i++) {
            if (userDB.get(i).getUsername().equals(username))
                return i;
        }
        return -1;
    }

    public boolean checkPremium (String isPremium){
        return isPremium.equals("true") || isPremium.equals("false");
    }
}
