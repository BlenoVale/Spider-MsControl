package util;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Bleno Vale
 */
public class TableTextAreaRenderer extends JTextArea implements TableCellRenderer {

    public TableTextAreaRenderer() {
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setFont(new java.awt.Font("Tahoma", 0, 11));
        this.setMargin(new java.awt.Insets(5, 5, 5, 5));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setText(value.toString());

        setText((value == null) ? "" : value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(),
                getPreferredSize().height);

        if (table.getRowHeight(row) < getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }

        return this;
    }

}
