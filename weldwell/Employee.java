package weldwell;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTable;

class User {

    public static User adminCreds = new User("amin", "admin");
    public static User userCreds = new User("user", "user");

    private final String username;
    private final String password;
 
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() 
    {
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

class Employee {

    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String workType;
    private double rate;
    private double grossPay;
    private double netPay;

    public Employee(String id, String firstName, String lastName, String address, String workType, double rate,
            double gross, double net) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.workType = workType;
        this.rate = rate;
        this.grossPay = gross;
        this.netPay = net;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public static ArrayList<Employee> employees = new ArrayList<>();

    public static void addEmployee(Employee employee, String FILE_NAME) {
        employees.add(employee);
        FileHand.saveDataToFile(FILE_NAME); // Save data whenever an employee is added
    }

    public static void removeEmployee(JTable table) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = (String) table.getModel().getValueAt(selectedRow, 0); // Assuming ID is in column 0
        Employee employeeToRemove = null;
        for (Employee employee : Employee.getEmployees()) {
            if (employee.getId().equals(id)) {
                employeeToRemove = employee;
                break;
            }
        }

        if (employeeToRemove == null) {
            System.out.println("Employee not found");
            return;
        }

        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to remove " + employeeToRemove.getFirstName() + " "
                        + employeeToRemove.getLastName() + "?",
                "Remove Row Confirmation", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            Employee.getEmployees().remove(employeeToRemove); // Remove from employee list
        }
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    // Method to calculate gross pay
    public static double calculateGrossPay(double daysWorked, double wagePerDay) {
        return daysWorked * wagePerDay;
    }

    public static double calculateNetPay(double rate) {
        // Implement logic to calculate net pay based on rate and deductions (if any)
        return calculateGrossPay(rate, 1000.00); // Placeholder for illustrative purposes (assuming no deductions)
    }

}
