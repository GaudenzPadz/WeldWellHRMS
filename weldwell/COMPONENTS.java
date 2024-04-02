package weldwell;

/*
REFERENCE:  
            
            
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class COMPONENTS {

    static JPanel sidePanel;
    static JPanel contentPane;
    static JFrame frame;

    static JComponent sidePanel(JPanel titlePane, JPanel buttonsPane, JPanel logoutPane) {
        sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(200, 10));

        sidePanel.add(titlePane, BorderLayout.NORTH);
        sidePanel.add(buttonsPane, BorderLayout.CENTER);
        sidePanel.add(logoutPane, BorderLayout.SOUTH);

        return sidePanel;
    }

    static JComponent contenPanel(JPanel contentPanel) {
        contentPane = new JPanel();

        contentPane.add(contentPanel);

        return contentPane;
    }

    public static JFrame GUI(String title, JComponent sidePanel, JComponent contentPanel, boolean visible) {

        frame = new JFrame(title);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(visible);
        // Set the content pane
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        frame.setContentPane(mainPanel);

        return frame;
    }

    public static JFrame GUI(String title, JComponent contentPanel, int width, int height, boolean resize,
            boolean visible) {
        frame = new JFrame(title);
        frame.setSize(width, height);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(resize);

        frame.setLocationRelativeTo(null); // Center on screen

        // frame.setUndecorated(true);
        // frame.setShape(new RoundRectangle2D.Double(10, 10, 100, 100, 50, 50));

        frame.setContentPane(contentPanel);
        frame.setVisible(visible);
        return frame;
    }

    private static final int ARC_SIZE = 30; // Adjust corner arc size as needed

    public static class round extends JTextField {

        public round(int columns) {
            super(columns);
            setOpaque(false); // Enable custom painting
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooth edges
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
        }
    }

    public static class roundPass extends JPasswordField {

        public roundPass(int columns) {
            super(columns);
            setOpaque(false); // Enable custom painting
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooth edges
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
        }
    }
}
