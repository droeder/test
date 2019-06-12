package demo.swing.table;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import static demo.swing.table.AsuTableModel.Columns.NAMES;
import static demo.swing.table.AsuTableModel.Columns.*;
import static java.util.Arrays.*;

public class Main {

    private static Container contentPane;

//    public static final boolean[] showAll = new boolean[]{true};
    public static boolean showAll = true;

    public static void main(String[] args) {

        final JFrame frame = new JFrame("Demo");
        contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());

        final AsuTableModel model = new AsuTableModel();
        JTable table = new AsuTable(model);

        addFilter(model, table);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void addFilter(AsuTableModel model, JTable table) {
        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
            public boolean include(Entry entry) {
                if (!showAll){
                    System.out.println("entry.getValue() = " + entry.getValue(0));
                    final Object currentValue = entry.getValue(0);
                    final List<String> maxValues = asList("AK1111555-3", "AK1111555-3", "AK2223666-4");
                    return maxValues.contains(currentValue);

                }
                return true;
            }
        };
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        sorter.setRowFilter(filter);
        table.setRowSorter(sorter);

        JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(action -> {
            showAll = !showAll;
            System.out.println("setting showall to  = " + showAll);
            model.fireTableDataChanged();
        });

        contentPane.add(checkBox);
        contentPane.add(new JScrollPane(table));
    }
}

enum Result {
    OK, NOK
}


class AsuTable extends JTable {

    private JComboBox stateBox;
    private JComboBox resultBox;

    public AsuTable(TableModel model) {
        super(model);


        final Dimension size = new Dimension(500, 80);
        setSize(size);
        setPreferredScrollableViewportSize(size);
        final AsuTableButtonRenderer tableButtonRenderer = new AsuTableButtonRenderer();
        addMouseListener(new AsuTableButtonMouseListener(this));

        setupCellRenderers(tableButtonRenderer);

        initColumnSizes(this);
    }

    private void setupCellRenderers(AsuTableButtonRenderer tableButtonRenderer) {
        final TableColumn stateColumn = getColumn(NAMES[COL_START_DATE]);
        stateColumn.setCellRenderer(tableButtonRenderer);

        stateBox = setComboboxCell(COL_RESULT, new SomeData[] { SomeData.OK, SomeData.NOK });


        resultBox = setComboboxCell(COL_STATUS, new String[] { "Abgestimmt", "neu", "In Abstimmung" });
    }

    private void initColumnSizes(JTable table) {

        final TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(COL_STATUS).setPreferredWidth(30);
        columnModel.getColumn(COL_CC).setPreferredWidth(30);
        columnModel.getColumn(COL_START_DATE).setPreferredWidth(20);
        columnModel.getColumn(COL_END_DATE).setPreferredWidth(20);
        columnModel.getColumn(COL_RESULT).setPreferredWidth(20);
    }

    class ResultRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value instanceof SomeData){
                final SomeData r = (SomeData) value;
                super.setValue( r == SomeData.OK ? "R_IS_OK" : "R_IS_nOK_");
            } else {
                super.setValue(value);
            }

        }

    }

    private <T> JComboBox setComboboxCell(int col, T[] options) {
        DefaultTableCellRenderer renderer = new ResultRenderer();

        final TableColumn column = getColumn(NAMES[col]);
        getColumnModel().getColumn(col).setCellRenderer(renderer);
        column.setCellRenderer(renderer);
        JComboBox jcBox =
//                new JComboBox<>(options);
        new JComboBox<>(new DefaultComboBoxModel<T>(options){
            public void setSelectedItem(Object anItem){
                System.out.println("anItem = " + anItem);
                super.setSelectedItem(anItem);
            }

            public Object getSelectedItem(){
                final Object selectedItem = super.getSelectedItem();
                System.out.println("selectedItem = " + selectedItem);
                return selectedItem;
            }

        });
        column.setCellEditor(new DefaultCellEditor(jcBox));

        return jcBox;
    }

    private static class AsuTableButtonRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int col) {
            return  (col == COL_START_DATE && value instanceof JButton)
                    ? (JButton)value
                    : super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        }
    }

    private static class AsuTableButtonMouseListener extends MouseAdapter {
        private final JTable table;

        public AsuTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / table.getRowHeight();

            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    ((JButton) value).doClick();
                }
            }
        }
    }
}

class AsuTableModel extends AbstractTableModel {

    public static final Object[][] DATABASE =
            {
                    { "AK1234567-1", "Abgestimmt",      "01.02.2019", SomeData.OK, SomeData.OK },
                    { "AK1234567-3", "neu",             null, SomeData.OK, SomeData.NOK },
                    { "AK1111555-2", "neu",             "01.10.2018", SomeData.OK, SomeData.OK },
                    { "AK1111555-3", "neu",             "01.10.2018", SomeData.OK, SomeData.OK },
                    { "AK2223666-1", "In Abstimmung",   "01.01.2018", SomeData.OK, SomeData.OK },
                    { "AK2223666-4", "In Abstimmung",   null, SomeData.OK, SomeData.OK }
            };

    public static final class Columns {
        public final static int COL_STATUS = 1;
        public final static int COL_CC = 0;
        public final static int COL_START_DATE = 2;
        public final static int COL_END_DATE = 4;
        public final static int COL_RESULT = 3;
        public final static int COL_START_BUTTON = 5;

        public final static String[] NAMES =
                { "ÄK", "Bearb. Stand", "Start", "Ergebnis", "Erledigt"};
    }

    public void setValueAt(Object value, int row, int col) {
        DATABASE[row][col] = value;
        if (col == COL_START_DATE){
//            if (value instanceof )
        }

        fireTableCellUpdated(row, col);

    }

    @Override
    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return  (col != COL_START_DATE);
    }

    @Override
    public int getRowCount() {
        return DATABASE.length;
    }

    @Override
    public int getColumnCount() {
        return NAMES.length;
    }

    @Override
    public String getColumnName(int col) {
        return NAMES[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value = DATABASE[row][col];

        switch (col) {
        case COL_END_DATE:
            if (value instanceof SomeData){
                System.out.println("result: value = " + value);
                SomeData r = (SomeData)value;
                if (r == SomeData.OK){
                    return "THIS IS OK";
                } else {
                    return "well then";
                }
            }
            break;
        case COL_START_DATE:
            if (value == null){
//                System.out.println("null == value = " + value);
                final String status = (String) value;
                final JButton button = new JButton(status);
                button.addActionListener(a -> System.out.println("msg = " + ("Button clicked for row " + row)));
                return button;
            }
            if (value instanceof Date || value instanceof String){
//                System.out.println("date: value = " + value);
            }
        }

        return value;
    }

}


enum SomeData {
    OK, NOK
}
