package weldwell;

public class Main {

    static GUI loginGUI;
    public static LOGIN loginFrame;
    
    public static void main(String[] args) {
        loginFrame = new LOGIN(User.adminCreds, User.userCreds);
        loginFrame.componentsPanel.setOpaque(false);
        loginFrame.welcomePanel.setOpaque(false);

        loginGUI = new GUI("Login", loginFrame, 400, 650, false, true);
    }   

}
