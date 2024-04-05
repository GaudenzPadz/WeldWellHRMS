package weldwell;

import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

class Employee {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String workType;
    private double rate;
    private double grossPay;
    private double netPay;
    private String gender;
    private ArrayList<String> allowances; // Use List for type safety
    private ArrayList<String> deductions;
    private int absents;
    private int late;
    private int dayOff;
    private double overtime;
    private String birthday; // Add birthday for personal information
    private double SSS;
    private double PAG_IBIG;
    private double PHILHEALTH;

    // Constants for work types and their corresponding wage per day
    private double SMAW_WAGE = 500;
    private double GTAW_WAGE = 900;
    private double FCAW_WAGE = 900;
    private double GMAW_WAGE = 1000;

    public Employee(String id, String firstName, String lastName, String address, String workType, double rate,
            double grossPay, double netPay, String gender) {
        // Initialize
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.workType = workType;
        this.rate = rate;
        this.grossPay = grossPay;
        this.netPay = netPay;
        this.gender = gender;
       // this.middleName = middleName;

        // not intialize (late nalang)
        this.allowances = new ArrayList<>();
        this.deductions = new ArrayList<>();
        this.absents = 0;
        this.late = 0;
        this.dayOff = 0;
        this.overtime = 0.0;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName= middleName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPAG_IBIG() {
        return PAG_IBIG;
    }

    public void setPAG_IBIG(double PAG_IBIG) {
        this.PAG_IBIG = PAG_IBIG;
    }

    public double getSSS() {
        return SSS;
    }

    public void setSSS(double SSS) {
        this.SSS = SSS;
    }

    public double getPHILHEALTH() {
        return PHILHEALTH;
    }

    public void setPHILHEALTH(double PHILHEALTH) {
        this.PHILHEALTH = PHILHEALTH;
    }

    public String getbirthday() {
        return birthday;
    }

    public void setbirthday(String birthday) {
        this.birthday = birthday;
    }

    public ArrayList<String> getAllowances() {
        return allowances;
    }

    public void addAllowance(String allowance) {
        this.allowances.add(allowance);
    }

    public ArrayList<String> getDeductions() {
        return deductions;
    }

    public void addDeduction(String deduction) {
        this.deductions.add(deduction);
    }

    public int getAbsents() {
        return absents;
    }

    public void setAbsents(int absents) {
        this.absents = absents;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getDayOff() {
        return dayOff;
    }

    public void setDayOff(int dayOff) {
        this.dayOff = dayOff;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    // Method to calculate deductions
    public double calculateDeductions(double grossPay) {
        double sssDeduction = grossPay * getSSS();
        double pagIbigDeduction = grossPay * getPAG_IBIG();
        double philHealthDeduction = grossPay * getPHILHEALTH();
        return sssDeduction + pagIbigDeduction + philHealthDeduction;
    }

    // Method to calculate gross pay
    public static double calculateGrossPay(double daysWorked, double wagePerDay) {
        return daysWorked * wagePerDay;
    }

    // Method to calculate net pay
    public static double calculateNetPay(double grossPay, double deductions) {
        return grossPay - deductions;
    }

    // Method to record attendance
    public void recordAttendance(String timeIn, String timeOut) {
        // Implement logic to record attendance
        LocalTime in = LocalTime.parse(timeIn);
        LocalTime out = LocalTime.parse(timeOut);

        if (in.isAfter(LocalTime.of(8, 0))) { // Assuming 8:00 AM is the start time
            // Employee is late
            this.late++;
        }

        // Assuming 5:00 PM is the end time
        if (out.isAfter(LocalTime.of(17, 0))) {
            // Calculate overtime
            this.overtime += out.getHour() - 17 + ((double) out.getMinute() / 60);
        }
    }

    // Method to apply for leave
    public void applyForLeave(int days) {
        // Implement logic to apply for leave
        this.dayOff += days;
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

}
