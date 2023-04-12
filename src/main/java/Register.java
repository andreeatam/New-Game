import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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

    public User user;

    public JPanel getPanel1() {
            return JPanel1;
        }
        public Register() {

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
                    loginFrame.setContentPane(new Login().getPanel0());
                }
            });
        }
        private void registerUser() {
            String firstName = tfFirstName.getText();
            String lastName = tfLastName.getText();
            String username = tfUsername.getText();
            String password = String.valueOf(pfPassword.getPassword());
            String confirmPassword = String.valueOf(pfPassword.getPassword());
            String city = tfCity.getText();

            if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || city.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Va rog completati toate campurile", "Incercati din nou", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Parolele nu coincid", "Incercati din nou", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean isAdded = addUserToDataBase(firstName, lastName, username, password, city);

            if (isAdded) {
                JOptionPane.showMessageDialog(this, "User creat cu succes!");
            }

        }

        private boolean addUserToDataBase(String firstName, String lastName, String username, String password, String city) {
            User user = new User();

            final String DB_URL = "jdbc:mysql://localhost:3306/game";
            final String USERNAME = "root";
            final String PASSWORD = "Andreea31!";

            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); //connection succesfully

                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO Users(firstName,lastName,username,password,city,tokens,ranking) " + "VALUES (?,?,?,?,?,2,2)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, city);


                try {
                    //insert row into table
                    int addedRows = preparedStatement.executeUpdate();
                    if (addedRows > 0) {
                        user.firstName = firstName;
                        user.lastName = lastName;
                        user.username = username;
                        user.password = password;
                        user.city = city;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Username-ul e deja folosit", "Va rugam alegeti altul!", JOptionPane.ERROR_MESSAGE);
                    stmt.close();
                    conn.close();
                    return false;

                }
                stmt.close();
                conn.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
}



