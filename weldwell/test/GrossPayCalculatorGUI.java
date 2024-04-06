
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GrossPayCalculatorGUI extends JFrame implements ActionListener {
    private JTextField txtBasicSalary, txtAllowances, txtSSS, txtPagibig, txtTaxRate, txtGrossPay, txtTotalDeduction, txtNetSalary;
    private JButton btnCalculate;
    private GrossPayCalculator calculator;

    public GrossPayCalculatorGUI() {
        super("Gross Pay Calculator");

        // Create instance of GrossPayCalculator
        calculator = new GrossPayCalculator();

        // Set layout to BorderLayout
        setLayout(new BorderLayout());

        // Panel for input fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        // Add components to input panel
        inputPanel.add(new JLabel("Basic Salary:"));
        txtBasicSalary = new JTextField();
        inputPanel.add(txtBasicSalary);

        inputPanel.add(new JLabel("Allowances:"));
        txtAllowances = new JTextField();
        inputPanel.add(txtAllowances);

        inputPanel.add(new JLabel("SSS:"));
        txtSSS = new JTextField();
        inputPanel.add(txtSSS);

        inputPanel.add(new JLabel("Pag-IBIG:"));
        txtPagibig = new JTextField();
        inputPanel.add(txtPagibig);

        inputPanel.add(new JLabel("Tax Rate (e.g., 0.15 for 15%):"));
        txtTaxRate = new JTextField();
        inputPanel.add(txtTaxRate);

        // Panel for result fields and labels
        JPanel resultPanel = new JPanel(new GridLayout(3, 2));

        // Add components to result panel
        resultPanel.add(new JLabel("Gross Pay:"));
        txtGrossPay = new JTextField();
        txtGrossPay.setEditable(false);
        resultPanel.add(txtGrossPay);

        resultPanel.add(new JLabel("Total Deduction:"));
        txtTotalDeduction = new JTextField();
        txtTotalDeduction.setEditable(false);
        resultPanel.add(txtTotalDeduction);

        resultPanel.add(new JLabel("Net Salary:"));
        txtNetSalary = new JTextField();
        txtNetSalary.setEditable(false);
        resultPanel.add(txtNetSalary);

        // Panel for the calculate button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(this); // Register button action listener
        buttonPanel.add(btnCalculate);

        // Add input panel, result panel, and button panel to frame
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set frame properties
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    // Action listener for the calculate button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            try {
                // Get input values from text fields
                double basicSalary = Double.parseDouble(txtBasicSalary.getText());
                double allowances = Double.parseDouble(txtAllowances.getText());
                double sss = Double.parseDouble(txtSSS.getText());
                double pagibig = Double.parseDouble(txtPagibig.getText());
                double taxRate = Double.parseDouble(txtTaxRate.getText());

                // Set input values to calculator object
                calculator.setBasicSalary(basicSalary);
                calculator.setAllowances(allowances);
                calculator.setSss(sss);
                calculator.setPagibig(pagibig);
                calculator.setTaxRate(taxRate);

                // Calculate gross pay, total deduction, and net salary
                calculator.calculate();

                // Display calculated values in text fields
                txtGrossPay.setText(String.valueOf(calculator.getGrossPay()));
                txtTotalDeduction.setText(String.valueOf(calculator.getTotalDeduction()));
                txtNetSalary.setText(String.valueOf(calculator.getNetSalary()));
            } catch (NumberFormatException ex) {
                // Handle invalid input
                JOptionPane.showMessageDialog(this, "Please enter valid numerical values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Create GUI on the event dispatch thread
        SwingUtilities.invokeLater(GrossPayCalculatorGUI::new);
    }
}

class GrossPayCalculator {
    private double basicSalary;
    private double allowances;
    private double sss;
    private double pagibig;
    private double taxRate;

    private double grossPay;
    private double totalDeduction;
    private double netSalary;

    // Default constructor
    public GrossPayCalculator() {
        // Initialize fields
        this.basicSalary = 0.0;
        this.allowances = 0.0;
        this.sss = 0.0;
        this.pagibig = 0.0;
        this.taxRate = 0.0;
    }

    // Parameterized constructor
    public GrossPayCalculator(double basicSalary, double allowances, double sss, double pagibig, double taxRate) {
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.sss = sss;
        this.pagibig = pagibig;
        this.taxRate = taxRate;
    }

    // Getters and setters for basicSalary
    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    // Getters and setters for allowances
    public double getAllowances() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    // Getters and setters for sss
    public double getSss() {
        return sss;
    }

    public void setSss(double sss) {
        this.sss = sss;
    }

    // Getters and setters for pagibig
    public double getPagibig() {
        return pagibig;
    }

    public void setPagibig(double pagibig) {
        this.pagibig = pagibig;
    }

    // Getters and setters for taxRate
    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    // Method to calculate gross pay, total deduction, and net salary
    public void calculate() {
		//replce llowance to attendance 
        grossPay = basicSalary + allowances;
        totalDeduction = sss + pagibig + (grossPay * taxRate);
        netSalary = grossPay - totalDeduction;
    }

    // Getters for calculated values
    public double getGrossPay() {
        return grossPay;
    }

    public double getTotalDeduction() {
        return totalDeduction;
    }

    public double getNetSalary() {
        return netSalary;
    }
}