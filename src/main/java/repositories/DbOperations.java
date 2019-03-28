package repositories;

import models.User;

import static repositories.ConnectDatabase.userDB;

public class DbOperations {
    public boolean checkUsername(String username) {
        for (User user : userDB) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public boolean checkPremium(String isPremium) {
        return isPremium.equals("true") || isPremium.equals("false");
    }
}
