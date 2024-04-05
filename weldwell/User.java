package weldwell;

import java.util.HashMap;

public class User {

    public static User adminCreds = new User("amin", "admin");
    public static User userCreds = new User("user", "user");

    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
        this.password = "";
        this.username = "";

    }

    public boolean checkCredentials(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    HashMap<Integer, Boolean> absences = new HashMap<>();

    public void setAbsent(int day) {
        absences.put(day, true);
    }

    public boolean isAbsent(int day) {
        return absences.getOrDefault(day, false);
    }
}