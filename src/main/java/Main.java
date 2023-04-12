import GUI.View;
import Model.User;

import javax.swing.*;

public class Main {
    public static void main(String[] args)  {
        JFrame frame = new JFrame("Register");
        frame.setSize(500,500);
        frame.setContentPane(new Register().getPanel1());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}