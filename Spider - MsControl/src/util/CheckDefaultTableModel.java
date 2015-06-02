package util;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BlenoVale
 */
public class CheckDefaultTableModel extends DefaultTableModel {

    private boolean isEditable;

    public CheckDefaultTableModel(Object[] columnNames, int rowCount, boolean isEditable) {
        super(columnNames, rowCount);
        this.isEditable = isEditable;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (0 == column) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0) {
            return true;
        } else {
            return isEditable;
        }
    }

}
