package util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableColorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        // เรียกใช้ของเดิมก่อน
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // แปลงค่าเป็น double (ถ้า value ไม่ใช่ double จะเป็น 0)
        double percent = 0;
        if (value instanceof Number) {
            percent = ((Number) value).doubleValue();
        }

        // เปลี่ยนสีตามค่าเปอร์เซ็นต์
        if (percent <= 0) {
            cell.setBackground(Color.RED);
        } else if (percent < 50) {
            cell.setBackground(Color.YELLOW);
        } else {
            cell.setBackground(Color.GREEN);
        }

        // จัดข้อความให้อยู่กึ่งกลาง และแสดงเป็น "xx.x%"
        setHorizontalAlignment(SwingConstants.CENTER);
        setText(String.format("%.1f%%", percent));

        return cell;
    }
}
