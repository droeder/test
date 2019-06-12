package demo.swing.combobox;

import javax.swing.*;
import java.awt.*;

public class ComboBoxWithEmptyValue {

    private static Container contentPane;

    public static void main(String[] args) {
        final JComboBox<MyData> box = new JComboBox<>(MyData.values());
        box.addItem(null);
        box.setSelectedIndex(-1);

        renderInFrame(new JScrollPane(box));
    }

    private static void renderInFrame(JComponent component) {
        final JFrame frame = new JFrame("Demo");
        contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());

        contentPane.add(component);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}



enum MyData {
    OK, NOK
}
