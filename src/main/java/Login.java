import GUI.Controller;
import GUI.View;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JDialog {

    private JPasswordField pfPassword;
    private JTextField tfUsername;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton clickToRegisterButton;
    private JPanel JPanel0;

    public JPanel getPanel0() {
        return JPanel0;
    }

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Validare()) {
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
                registerFrame.setContentPane(new Register().getPanel1());
            }
        });
    }


    private boolean Validare(){
        String username=tfUsername.getText();
        String password=String.valueOf(pfPassword.getPassword());
        boolean ok=false;

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Va rog completati toate campurile", "Incercati din nou", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            User user=verifyUser();
            if(user==null){
                JOptionPane.showMessageDialog(this, "Username-ul sau parola este gresita!", "Va rugam incercati din nou!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                if (user.username.equals(username) && (user.password.equals(password))) {
                    JOptionPane.showMessageDialog(this, "Login creat cu succes!");
                    dispose();
                    JFrame frame = new JFrame("Calculator de Polinoame");
                    frame.setSize(300, 300);
                    frame.setContentPane(new View().getContentPane());
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.pack();
                    ok = true;
                }
            }
        }


        return ok;
    }

    private User verifyUser(){
        User user=null;
        String usernameLogin=tfUsername.getText();
        String password=String.valueOf(pfPassword.getPassword());

        final String DB_URL = "jdbc:mysql://localhost:3306/game";
        final String USERNAME = "root";
        final String PASSWORD = "Andreea31!";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); //connection succesfully

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Users WHERE username=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, usernameLogin);
            preparedStatement.setString(2, password);

           try {
               ResultSet resultSet=preparedStatement.executeQuery();
               if (resultSet.next()) {
                   Controller.user.firstName = resultSet.getString("firstName");
                   Controller.user.lastName = resultSet.getString("lastName");
                   Controller.user.username = resultSet.getString("username");
                   Controller.user.password = resultSet.getString("password");
                   Controller.user.city = resultSet.getString("city");
                   Controller.user.tokens = Integer.parseInt(resultSet.getString("tokens"));
                   Controller.user.ranking = Integer.parseInt(resultSet.getString("ranking"));

               }
           }catch (Exception e){
               stmt.close();
               conn.close();
               return null;
           }

           stmt.close();
           conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Controller.user;

    }



}
