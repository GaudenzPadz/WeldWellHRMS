import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class EmployeeAttendanceSystem extends JFrame {
    private HashMap<String, String> attendanceMap;
    private JTextField nameField;
    private JComboBox<String> statusComboBox;
    private JButton datePickerButton;
    private JLabel dateLabel;

    public EmployeeAttendanceSystem() {
        attendanceMap = new HashMap<>();

        setTitle("Employee Attendance System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Employee Name:");
        nameField = new JTextField();

        dateLabel = new JLabel("Date:");
        datePickerButton = new JButton("Pick Date");
        datePickerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pickDate();
            }
        });

        JLabel statusLabel = new JLabel("Attendance Status:");
        statusComboBox = new JComboBox<>();
        statusComboBox.addItem("Present");
        statusComboBox.addItem("Absent");
        statusComboBox.addItem("Leave");
        statusComboBox.addItem("Day Off");

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String status = (String) statusComboBox.getSelectedItem();
                attendanceMap.put(name + " - " + dateLabel.getText(), status);
                JOptionPane.showMessageDialog(null,
                        "Attendance for " + name + " on " + dateLabel.getText() + " recorded as " + status);
            }
        });

        JButton viewButton = new JButton("View Attendance");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder attendanceReport = new StringBuilder("Attendance Report:\n");
                for (String key : attendanceMap.keySet()) {
                    attendanceReport.append(key).append(": ").append(attendanceMap.get(key)).append("\n");
                }
                JOptionPane.showMessageDialog(null, attendanceReport.toString());
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(dateLabel);
        panel.add(datePickerButton);
        panel.add(statusLabel);
        panel.add(statusComboBox);
        panel.add(submitButton);
        panel.add(viewButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void pickDate() {
        JFrame frame = new JFrame("Pick Date");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new FlowLayout());

        // Create a SpinnerDateModel with time format
        SpinnerDateModel spinnerDateModel = new SpinnerDateModel();
        spinnerDateModel.setCalendarField(Calendar.MINUTE); // Set to minute precision

        // Create the JSpinner with the SpinnerDateModel
        JSpinner spinner2 = new JSpinner(spinnerDateModel);
        // Set time format to 12-hour format with AM/PM indication
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner2, "hh:mm a");
        spinner2.setEditor(editor);

        Date selectedDate = (Date) spinner2.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        dateLabel.setText(dateFormat.format(selectedDate));

        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd"));
        panel.add(spinner2);

        

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = (Date) spinner.getValue();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateLabel.setText(dateFormat.format(selectedDate));
                frame.dispose();
            }
        });
        panel.add(okButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeAttendanceSystem();
    }
}
