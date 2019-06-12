package demo.swing.spinglayout;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class Main {

    public static void main(String... args){

        final JFrame frame = new JFrame("Demo");
        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        final JLabel label = new JLabel("Label: ");
        final JButton btn = new JButton("S");
        contentPane.setLayout(layout);
        contentPane.add(label);
        contentPane.add(btn);

        final JTextField textField = new JTextField("Text field", 15);
        contentPane.add(textField);
        layout.putConstraint(WEST, label,5, WEST, contentPane);
        layout.putConstraint(NORTH, label,5, NORTH, contentPane);

        layout.putConstraint(WEST, btn, 5, EAST, label);
        layout.putConstraint(NORTH, btn, 5, NORTH, contentPane);

        layout.putConstraint(WEST, textField, 30, EAST, label);
        layout.putConstraint(NORTH, textField, 5, NORTH, contentPane);

        frame.pack();
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

}
