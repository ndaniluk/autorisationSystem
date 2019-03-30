package models;

public class User {
    private String username;
    private String password;
    private String email;
    private boolean isPremium;
    private boolean isAdmin;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isPremium = false;
        this.isAdmin = true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
