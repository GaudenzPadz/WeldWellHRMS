package weldwell;

public class Main {
    public static final String FILE_NAME = "data.csv";

    public static Employee employee;

    public static LOGIN loginFrame;
    public static GUI loginGUI;

    // Admin GUI Object and ADMIN object
    public static ADMIN adminPanel = new ADMIN();
    public static GUI adminGUI;

    public static void load() {
        // Load data from CSV file
        FileHand.loadData(FILE_NAME);
      
        // Retrieve the employee by ID after loading the data
        employee = FileHand.getEmployeeById("DEV001");
        
        // Check if the employee is not null before accessing its methods
        if (employee != null) {
            System.out.println(employee.getId());
            // Set regularUser credentials using the employee's ID as username
            regularUser = new User(employee.getId(), "user");
        } else {
            // Handle case when employee is null
            System.out.println("Employee is null");
            // You may want to handle this case appropriately based on the system requirements
        }
    }
    
    
    // credentials
    public static User adminUser = new User("admin", "admin");
    public static User regularUser;

    // userpanel Object and
    public static GUI userGui;
    static Employee dev001 = FileHand.getEmployeeById("DEV001");
    public static USERPANEL userPanel = new USERPANEL();

    public static void main(String[] args) {
        load();
        // Create login frame and GUI
        loginFrame = new LOGIN(adminUser, regularUser);
        loginFrame.componentsPanel.setOpaque(false);
        loginFrame.welcomePanel.setOpaque(false);

        loginGUI = new GUI("Login", loginFrame, 400, 650, false, true);

    }
}
