package util;

import java.awt.Color;
import java.awt.Component;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class FixedColumnTable implements ChangeListener, PropertyChangeListener {

    private JTable main;
    private JTable fixed;
    private JScrollPane scrollPane;

    public FixedColumnTable(int fixedColumns, JScrollPane scrollPane) {
        this.scrollPane = scrollPane;

        main = ((JTable) scrollPane.getViewport().getView());
        main.setAutoCreateColumnsFromModel(false);
        main.addPropertyChangeListener(this);

        fixed = new JTable() {
            private static final long serialVersionUID = 4221305668526115726L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        fixed.setAutoCreateColumnsFromModel(false);
        fixed.setModel(main.getModel());
        fixed.setSelectionModel(main.getSelectionModel());
        fixed.setFocusable(false);
        fixed.getTableHeader().setReorderingAllowed(false);

        fixed.setGridColor(Color.LIGHT_GRAY);

        final TableCellRenderer tcrOs = fixed.getTableHeader().getDefaultRenderer();
        fixed.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                JLabel lbl = (JLabel) tcrOs.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);
                lbl.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.LIGHT_GRAY));
                if (column == 1)
                    lbl.setBackground(new Color(230, 230, 230));
                return lbl;
            }
        });

        for (int i = 0; i < fixedColumns; i++) {
            TableColumnModel columnModel = main.getColumnModel();
            TableColumn column = columnModel.getColumn(0);
            columnModel.removeColumn(column);
            fixed.getColumnModel().addColumn(column);
        }

        fixed.setPreferredScrollableViewportSize(fixed.getPreferredSize());
        scrollPane.setRowHeaderView(fixed);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixed.getTableHeader());

        scrollPane.getRowHeader().addChangeListener(this);
    }

    public JTable getFixedTable() {
        return fixed;
    }

    public void stateChanged(ChangeEvent e) {
        JViewport viewport = (JViewport) e.getSource();
        scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
    }

    public void propertyChange(PropertyChangeEvent e) {

        if ("selectionModel".equals(e.getPropertyName())) {
            fixed.setSelectionModel(main.getSelectionModel());
        }

        if ("model".equals(e.getPropertyName())) {
            fixed.setModel(main.getModel());
        }
    }
}
