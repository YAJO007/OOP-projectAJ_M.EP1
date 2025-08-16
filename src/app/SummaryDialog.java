package app;

import javax.swing.*;
import java.awt.*;

public class SummaryDialog extends JDialog {
    public SummaryDialog(JFrame parent, JTable table) {
        super(parent, "Gas Volume Summary", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        int rows = table.getRowCount();
        int cols = table.getColumnCount();

        if (rows == 0 || cols == 0) {
            add(new JLabel("No information yet", SwingConstants.CENTER), BorderLayout.CENTER);
            return;
        }

        int noGas = 0, gasBelow50 = 0, gasAbove50 = 0;

        // คำนวณจำนวนเซลล์แต่ละประเภท
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Object val = table.getValueAt(r, c);
                if (val instanceof Number) {
                    double v = ((Number) val).doubleValue();
                    if (v == 0) noGas++;
                    else if (v < 50) gasBelow50++;
                    else gasAbove50++;
                }
            }
        }

        int total = noGas + gasBelow50 + gasAbove50;
        double pNoGas = (noGas * 100.0) / total;
        double pBelow = (gasBelow50 * 100.0) / total;
        double pAbove = (gasAbove50 * 100.0) / total;

        // Text Area สำหรับสรุปแบบละเอียด
        JTextArea textArea = new JTextArea(
                "Gas Volume Summary\n\n" +
                        "Total Cells: " + total + "\n" +
                        "No Gas: " + noGas + " cells (" + String.format("%.2f", pNoGas) + "%)\n" +
                        "Gas Below 50%: " + gasBelow50 + " cells (" + String.format("%.2f", pBelow) + "%)\n" +
                        "Gas Above 50%: " + gasAbove50 + " cells (" + String.format("%.2f", pAbove) + "%)"
        );
        textArea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        textArea.setEditable(false);
        textArea.setBackground(new Color(245, 245, 245));
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel สำหรับปุ่มสีแดง, ส้ม, เขียว
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(createColorPanel("No Gas", noGas, pNoGas, Color.RED, Color.WHITE));
        buttonPanel.add(createColorPanel("Gas < 50%", gasBelow50, pBelow, Color.ORANGE, Color.BLACK));
        buttonPanel.add(createColorPanel("Gas > 50%", gasAbove50, pAbove, Color.GREEN.darker(), Color.WHITE));

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // สร้าง Panel สำหรับแต่ละประเภท
    private JPanel createColorPanel(String title, int count, double percent, Color bgColor, Color fgColor) {
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setLayout(new GridLayout(2, 1));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(fgColor);

        JLabel countLabel = new JLabel(count + " (" + String.format("%.2f", percent) + "%)", SwingConstants.CENTER);
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        countLabel.setForeground(fgColor);

        panel.add(titleLabel);
        panel.add(countLabel);

        return panel;
    }
}
