// Login.java
package GUI;

import Service.UserService;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {
    private JPasswordField pfPassword;
    private JTextField tfUsername;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton clickToRegisterButton;
    private JPanel JPanel0;

    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
        initComponents();
    }

    private void initComponents() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
                Object source = e.getSource();
                if (source instanceof JButton) {
                    Component comp = (Component) source;
                    Window window = SwingUtilities.getWindowAncestor(comp);
                    if (window instanceof JFrame) {
                        JFrame frame = (JFrame) window;
                        frame.setVisible(false);
                        frame.dispose();
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        clickToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof JButton) {
                    Component comp = (Component) source;
                    Window window = SwingUtilities.getWindowAncestor(comp);
                    if (window instanceof JFrame) {
                        JFrame frame = (JFrame) window;
                        frame.setVisible(false);
                        frame.dispose();
                    }
                }
                JFrame registerFrame = new JFrame("Register");
                registerFrame.setSize(500, 500);
                registerFrame.setVisible(true);
                registerFrame.setContentPane(new Register(userService).getPanel1());
            }
        });
    }

    private void loginUser() {
        String username = tfUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vă rugăm completați toate câmpurile.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userService.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Autentificare reușită.");
            JFrame frame = new JFrame("Calculator de Polinoame");
            frame.setSize(300, 300);
            frame.setContentPane(new ConstructGUI(userService).getContentPane());
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
        } else {
            JOptionPane.showMessageDialog(this, "Nume de utilizator sau parolă incorecte.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanel0() {
        return JPanel0;
    }
}
