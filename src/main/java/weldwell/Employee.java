package weldwell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

class Employee {

    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String workType;
    private double rate;
    private double grossPay;
    private double netPay;

    public Employee(String id, String firstName, String lastName, String address, String workType, double rate, double gross, double net) {
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

    public static void loadData(String filePath) {
        employees.clear(); // Clear the existing list of employees

        //loadDataFromFile(String path)
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Employee employee = new Employee(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7]));
                employees.add(employee);
            }
            reader.close();
        } catch (IOException e) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Error loading data: " + e.getMessage() + "\n" + "Would you like to load a sample data?",
                    "Data Error",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                // Call your method to reload sample data into the table
                // Replace this comment with your actual code for reloading data
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.b2b"))) {
                    // Write sample data to the file
                    writer.write("1,Gaudenz,Padullon,123 Main St,Developer,50.0,2000.0,1800.0\n");
                    writer.write("2,Khier,Lapurga,456 Main St,Designer,50.0,2000.0,1800.0\n");
                    writer.close();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(null, "Error creating sample data file: " + a.getMessage());
                }

                // Load the sample data from source package into the table model
                loadData("data.b2b");
            }
            if (choice == JOptionPane.NO_OPTION) {
                //if no then leave it 
                //some suggestion for this option
            }
        }
    }

    public static void saveDataToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the data to the file
            for (Employee employee : Employee.getEmployees()) {
                writer.write(employee.getId() + ","
                        + employee.getFirstName() + ","
                        + employee.getLastName() + ","
                        + employee.getAddress() + ","
                        + employee.getWorkType() + ","
                        + employee.getRate() + ","
                        + employee.getGrossPay() + ","
                        + employee.getNetPay() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving data: " + e.getMessage());
        }
    }

    public static void addEmployee(Employee employee, String FILE_NAME) {
        employees.add(employee);
        saveDataToFile(FILE_NAME); // Save data whenever an employee is added
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
                "Are you sure you want to remove " + employeeToRemove.getFirstName() + " " + employeeToRemove.getLastName() + "?",
                "Remove Row Confirmation", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            Employee.getEmployees().remove(employeeToRemove);  // Remove from employee list
        }
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static double calculateGrossPay(double rate) {
        // Implement logic to calculate gross pay based on rate (e.g., hourly work)
        return rate * 40; // Placeholder for illustrative purposes
    }

    public static double calculateNetPay(double rate) {
        // Implement logic to calculate net pay based on rate and deductions (if any)
        return calculateGrossPay(rate); // Placeholder for illustrative purposes (assuming no deductions)
    }

}
