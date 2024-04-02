package weldwell;

/*
REFERENCE: https://github.com/iamrohitsuthar/JavaSwingLoginForm/blob/master/src/SwingSample.java
           https://www.tutorialspoint.com/how-can-we-add-padding-to-a-jtextfield-in-java
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class GUI extends JFrame {

    public GUI(String title, JComponent contentPanel, int width, int height, boolean resize, boolean visible) {

        setSize(width, height);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(resize);

        setLocationRelativeTo(null); // Center on screen

        //frame.setUndecorated(true);
        // frame.setShape(new RoundRectangle2D.Double(10, 10, 100, 100, 50, 50));
        setContentPane(contentPanel);
        setVisible(visible);

    }

}

class User {

    static User adminCreds = new User("amin", "admin");
    static User userCreds = new User("user", "user");

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

public final class LOGIN extends JPanel {
    
    public final JPanel componentsPanel, welcomePanel;
    private final JButton loginButton;
    private final JTextField email;
    private final JPasswordField password;
    private final JCheckBox showPassword;
    private final JLabel usernameLabel, passwordLabel, welcomeLabel, subLabel;
    public static JFrame adminFrame = new ADMIN();
    public final Image img = Toolkit.getDefaultToolkit().getImage("background.jpg");

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);

    }

    public void grid() {

        GridBagConstraints input = new GridBagConstraints();
        input.anchor = GridBagConstraints.WEST;
        input.insets = new Insets(0, 0, 2, 10);
        input.gridy = 1;
        componentsPanel.add(usernameLabel, input);

        input.anchor = GridBagConstraints.CENTER;
        input.gridy = 2;
        componentsPanel.add(email, input);

        input.anchor = GridBagConstraints.WEST;
        input.insets = new Insets(0, 0, 2, 10);

        input.gridy = 3;
        componentsPanel.add(passwordLabel, input);

        input.anchor = GridBagConstraints.CENTER;
        input.gridy = 4;
        componentsPanel.add(password, input);

        input.gridy = 5;
        input.insets = new Insets(0, 0, 2, 10);
        input.anchor = GridBagConstraints.WEST;
        componentsPanel.add(showPassword, input);

        input.insets = new Insets(20, 10, 10, 10);
        input.anchor = GridBagConstraints.CENTER;
        input.gridx = 0;
        input.gridy = 6;
        componentsPanel.add(loginButton, input);

        //add title and components to frame
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(welcomePanel, gbc);
        gbc.gridy = 1;
        add(componentsPanel, gbc);

        setSize(370, 650);

    }

    public void addEventListeners() {
        showPassword.addActionListener((ActionEvent e) -> {
            if (showPassword.isSelected()) {
                password.setEchoChar((char) 0);
                password.setForeground(Color.BLACK);
                
            } else {
                password.setEchoChar('*');
            }
        });

        loginButton.addActionListener((ActionEvent e) -> {
            String username = email.getText();
            String pass = new String(password.getPassword());
            
            processLogin(username, pass);
        });

        email.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().equals("")) {
                    email.setText("Enter your email");
                    email.setForeground(Color.gray);
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                if (email.getText().equals("Enter your email")) {
                    email.setText("");
                    email.setForeground(Color.BLACK);
                }
            }
        });

        password.addFocusListener(new FocusListener() {
            boolean firstTime = true;

            @Override
            public void focusLost(FocusEvent e) {
                if (password.getPassword().length == 0) {
                    password.setText("Enter your password");
                    password.setForeground(Color.GRAY);
                    password.setEchoChar((char) 0); // Hide password text
                    firstTime = true;
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (firstTime) {
                    password.setText("");
                    password.setForeground(Color.BLACK);
                    password.setEchoChar('*'); // Show password characters
                    firstTime = false;
                }
            }
        });

    }

    public final User admin;
    public final User user;
    //private User[] users;
    public LOGIN(User admin, User user) {
        this.admin = admin;
        this.user = user;
        welcomePanel = new JPanel(new GridLayout(3, 1));
        welcomeLabel = new JLabel("WELD WELL", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
        subLabel = new JLabel("Human Resource Management System", JLabel.CENTER);

        welcomePanel.add(welcomeLabel);
        welcomePanel.add(subLabel);

        // Components panel
        componentsPanel = new JPanel(new GridBagLayout());

        usernameLabel = new JLabel("Username:", JLabel.LEFT);
        email = new COMPONENTS.round(26);

        passwordLabel = new JLabel("Password:", JLabel.LEFT);
        password = new COMPONENTS.roundPass(26);
        showPassword = new JCheckBox("Show Password");

        loginButton = new JButton("Login");

        email.setPreferredSize(new Dimension(300, 40));
        password.setPreferredSize(new Dimension(300, 40));

        loginButton.setPreferredSize(new Dimension(350, 40));
        loginButton.setBackground(new Color(66, 245, 114));

        email.setText("Enter your email");
        email.setForeground(Color.gray);

        password.setForeground(Color.gray);
        password.setEchoChar((char) 0);

        usernameLabel.setFont(new Font("Sans Serif", Font.BOLD, 13));
        passwordLabel.setFont(new Font("Sans Serif", Font.BOLD, 13));
        email.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        password.setFont(new Font("Sans Serif", Font.PLAIN, 16));

        email.setMargin(new Insets(0, 10, 0, 5));
        password.setMargin(new Insets(0, 10, 0, 5));

        addEventListeners();
        grid();

        // frame.setUndecorated(true);
        // frame.setShape(new RoundRectangle2D.Double(10, 10, 100, 100, 50, 50));
        //COMPONENTS.GUI("Login", LOGIN.this, 400, 650, false, true);
    }

    public static void processLogin(String inputUsername, String inputPassword) {
        User adminUser = new User("admin", "admin");
        User regularUser = new User("user", "user");

        if (adminUser.checkCredentials(inputUsername, inputPassword)) {
            Main.loginGUI.dispose();
            adminFrame.setVisible(true);

        } else if (regularUser.checkCredentials(inputUsername, inputPassword)) {
            Main.loginGUI.dispose();

            //UserPanel.userPanel();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password");
        }
    }

    public static JPanel logoutPanel() {
        JPanel logoutPanel = new JPanel();
        JButton logout = new JButton("Logout");
        logout.addActionListener((ActionEvent e)
                -> {
            adminFrame.dispose();
            Main.loginGUI.revalidate();
            Main.loginGUI.repaint();
            Main.loginGUI.setVisible(true);
        });
        logoutPanel.add(logout);
        return logoutPanel;
    }
}
