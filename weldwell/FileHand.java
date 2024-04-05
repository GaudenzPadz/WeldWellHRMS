package weldwell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class FileHand {

    public static void loadData(String filePath) {
        Employee.employees.clear(); // Clear the existing list of employees

        // loadDataFromFile(String path)
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Employee employee = new Employee(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        Double.parseDouble(data[5]),
                        Double.parseDouble(data[6]),
                        Double.parseDouble(data[7]),
                        data[8] );
                Employee.employees.add(employee);
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

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.csv"))) {
                    // Write sample data to the file
                    writer.write("1,Gaudenz,Padullon,123 Main St,Developer,50.0,2000.0,1800.0,Male\n");
                    writer.write("2,Khier,Lapurga,456 Main St,Designer,50.0,2000.0,1800.0,Male\n");
                    writer.close();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(null, "Error creating sample data file: " + a.getMessage());
                }

                // Load the sample data from source package into the table model
                loadData("data.b2b");
            }
            if (choice == JOptionPane.NO_OPTION) {
                // if no then leave it
                // some suggestion for this option
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
                        + employee.getNetPay() + ","
                        + employee.getGender() 
                        // + ","
                       // + employee.getMiddleName()
                        + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving data: " + e.getMessage());
        }
    }

    public static Employee getEmployeeByName(String firstName, String lastName) {
        for (Employee employee : Employee.employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        return null; // If employee not found
    }

    public static Employee getEmployeeById(String id) {
        for (Employee employee : Employee.employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null; // If employee not found
    }

}
