import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

class RadioButtonRenderer implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null)
            return null;
        return (Component) value;
    }
}

class RadioButtonEditor extends DefaultCellEditor implements ItemListener {
    /**
     */
    private static final long serialVersionUID = 1L;
    private JRadioButton button;

    public RadioButtonEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (value == null)
            return null;
        button = (JRadioButton) value;
        button.addItemListener(this);
        return (Component) value;
    }

    public Object getCellEditorValue() {
        button.removeItemListener(this);
        return button;
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
        System.out.println("111111111");
    }
}

public class JRadioButtonTableExample extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JRadioButtonTableExample() {

        DefaultTableModel dm = new DefaultTableModel();
        Object [][]o= new Object[5][1];
        for(int i=0;i<5;i++){
            o[i][0]="Group 1";
        }
        Object [][]o2= new Object[5][2];
        for(int i=0;i<5;i++){
            o2[i][0]=o[i][0];
            o2[i][1]=new JRadioButton();
        }
        dm.setDataVector(o2, new Object[] {
                "String", "JRadioButton" });

        JTable table = new JTable(dm) {
            public void tableChanged(TableModelEvent e) {
                super.tableChanged(e);
                repaint();
            }
        };
        table.getColumn("JRadioButton").setCellRenderer(
                new RadioButtonRenderer());
        table.getColumn("JRadioButton").setCellEditor(
                new RadioButtonEditor(new JCheckBox()));
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize(200, 140);
        setVisible(true);
    }

    public static void main(String[] args) {
        JRadioButtonTableExample frame = new JRadioButtonTableExample();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}