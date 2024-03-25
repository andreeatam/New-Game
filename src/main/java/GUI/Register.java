// Register.java
package GUI;

import Service.UserService;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JDialog {
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JPasswordField pfPasswordRetype;
    private JTextField tfCity;
    private JButton registerButton;
    private JButton cancelButton;
    private JButton clickToLogin;
    private JPanel JPanel1;

    private final UserService userService;

    public Register(UserService userService) {
        this.userService = userService;
        initComponents();
    }

    private void initComponents() {

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        clickToLogin.addActionListener(new ActionListener() {
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
                JFrame loginFrame = new JFrame("Login");
                loginFrame.setSize(500, 500);
                loginFrame.setVisible(true);
                loginFrame.setContentPane(new Login(userService).getPanel0());
            }
        });
    }

    private void registerUser() {
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String username = tfUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfPasswordRetype.getPassword());
        String city = tfCity.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vă rugăm completați toate câmpurile.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Parolele nu coincid.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(firstName,lastName, username, password, city,0);

        if (userService.register(user)) {
            JOptionPane.showMessageDialog(this, "Utilizator înregistrat cu succes.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Utilizatorul există deja.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanel1() {
        return JPanel1;
    }
}
