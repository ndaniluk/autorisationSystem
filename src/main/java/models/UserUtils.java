package models;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static repositories.DbConnection.userDB;

public class UserUtils {
    public User retrieveUserFromRequest(HttpServletRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

//        PasswordAuth passwordAuth = new PasswordAuth();
//        password = passwordAuth.hashPassword(password); //TODO hash password

        return new User(username, password, email);
    }

    public int checkUsername(String username) {
        for (int i = 0; i < userDB.size(); i++) {
            if (userDB.get(i).getUsername().equals(username))
                return i;
        }
        return -1;
    }

    public void changePremiumState(boolean state, String username) {
        int userIndex = checkUsername(username);
        userDB.get(userIndex).setPremium(state);
    }
}
