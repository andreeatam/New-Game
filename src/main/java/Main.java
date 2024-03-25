
import GUI.Register;
import Service.UserService;

import javax.swing.*;

public class Main {
    public static void main(String[] args)  {
        UserService userServicee = new UserService();

        JFrame frame = new JFrame("Register");
        frame.setSize(500,500);
        frame.setContentPane(new Register(userServicee).getPanel1());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}