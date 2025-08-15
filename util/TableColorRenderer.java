package util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableColorRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
            JTable tbl, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {

        Component cell = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, col);
        double p = (double) value;

        if (p <= 0) {
            cell.setBackground(Color.RED);
        } else if (p < 50) {
            cell.setBackground(Color.YELLOW);
        } else {
            cell.setBackground(Color.GREEN);
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        setText(String.format("%.1f%%", p));
        return cell;
    }
}
