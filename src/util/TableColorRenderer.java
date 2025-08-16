package util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableColorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        // แปลงค่าที่ได้มาให้เป็นตัวเลขทศนิยม
        double percent = (double) value;

        // สร้าง component ของ cell ตามค่าเดิมก่อน
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // เปลี่ยนสีตามช่วงค่า
        if (percent <= 0) {
            cell.setBackground(Color.RED);
        } else if (percent < 50) {
            cell.setBackground(Color.YELLOW);
        } else {
            cell.setBackground(Color.GREEN);
        }
        // จัดให้อยู่กึ่งกลาง + แสดงผลแบบ 1 ตำแหน่งทศนิยมพร้อมเครื่องหมาย %
        setHorizontalAlignment(SwingConstants.CENTER);
        setText(String.format("%.1f%%", percent));

        return cell;
    }
}
