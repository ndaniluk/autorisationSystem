package user;

public class User {
    private int id;
    private String username;
    private boolean isPremium;

    public User(int id, String username, boolean isPremium) {
        this.id = id;
        this.username = username;
        this.isPremium = isPremium;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
}
