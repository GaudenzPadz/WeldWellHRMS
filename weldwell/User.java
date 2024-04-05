package weldwell;

public class User {

    public static User adminCreds = new User("amin", "admin");
    public static User userCreds = new User("user", "user");

    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkCredentials(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }
}