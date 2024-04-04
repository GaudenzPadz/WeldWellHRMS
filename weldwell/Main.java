package weldwell;

public class Main {

    public static LOGIN loginFrame;
    public static GUI loginGUI;
   
    //userpanel Object and   
    public static userGui userPanel = new userGui();
    public static GUI userGui;

    //Admin GUI Object and ADMIN object
    public static ADMIN adminPanel = new ADMIN();
    public static GUI adminGUI;

    public static void main(String[] args) {
        loginFrame = new LOGIN(User.adminCreds, User.userCreds);
        loginFrame.componentsPanel.setOpaque(false);
        loginFrame.welcomePanel.setOpaque(false);

        loginGUI = new GUI("Login", loginFrame, 400, 650, false, true);
    }   

}
