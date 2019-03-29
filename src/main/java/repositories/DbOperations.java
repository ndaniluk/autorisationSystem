package repositories;

import static repositories.ConnectDatabase.userDB;

public class DbOperations {
    public int checkUsername(String username) {
        for (int i = 0; i < userDB.size(); i++) {
            if (userDB.get(i).getUsername().equals(username))
                return i;
        }
        return -1;
    }

    public boolean checkPremium(String isPremium) {
        return isPremium.equals("true") || isPremium.equals("false");
    }
}
