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

        // ถ้าไม่มีข้อมูลเลย
        if (rows == 0 || cols == 0) {
            add(new JLabel("No information yet", SwingConstants.CENTER), BorderLayout.CENTER);
            return;
        }

        // ================== คำนวณจำนวนเซลล์ ==================
        int noGas = 0;
        int gasBelow50 = 0;
        int gasAbove50 = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Object val = table.getValueAt(r, c);
                if (val instanceof Number) {
                    double v = ((Number) val).doubleValue();
                    if (v == 0) {
                        noGas++;
                    } else if (v < 50) {
                        gasBelow50++;
                    } else {
                        gasAbove50++;
                    }
                }
            }
        }

        int total = noGas + gasBelow50 + gasAbove50;
        double pNoGas = (noGas * 100.0) / total;
        double pBelow = (gasBelow50 * 100.0) / total;
        double pAbove = (gasAbove50 * 100.0) / total;

        // ================== Text Area แสดงสรุป ==================
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
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // ================== Panel สำหรับแสดงผลด้วยสี ==================
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel No Gas
        JPanel noGasPanel = new JPanel(new GridLayout(2, 1));
        noGasPanel.setBackground(Color.RED);
        JLabel noGasLabel = new JLabel("No Gas", SwingConstants.CENTER);
        noGasLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        noGasLabel.setForeground(Color.WHITE);
        JLabel noGasCount = new JLabel(noGas + " (" + String.format("%.2f", pNoGas) + "%)", SwingConstants.CENTER);
        noGasCount.setFont(new Font("Segoe UI", Font.BOLD, 14));
        noGasCount.setForeground(Color.WHITE);
        noGasPanel.add(noGasLabel);
        noGasPanel.add(noGasCount);

        // Panel Gas < 50%
        JPanel belowPanel = new JPanel(new GridLayout(2, 1));
        belowPanel.setBackground(Color.ORANGE);
        JLabel belowLabel = new JLabel("Gas < 50%", SwingConstants.CENTER);
        belowLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        belowLabel.setForeground(Color.BLACK);
        JLabel belowCount = new JLabel(gasBelow50 + " (" + String.format("%.2f", pBelow) + "%)", SwingConstants.CENTER);
        belowCount.setFont(new Font("Segoe UI", Font.BOLD, 14));
        belowCount.setForeground(Color.BLACK);
        belowPanel.add(belowLabel);
        belowPanel.add(belowCount);

        // Panel Gas > 50%
        JPanel abovePanel = new JPanel(new GridLayout(2, 1));
        abovePanel.setBackground(Color.GREEN.darker());
        JLabel aboveLabel = new JLabel("Gas > 50%", SwingConstants.CENTER);
        aboveLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        aboveLabel.setForeground(Color.WHITE);
        JLabel aboveCount = new JLabel(gasAbove50 + " (" + String.format("%.2f", pAbove) + "%)", SwingConstants.CENTER);
        aboveCount.setFont(new Font("Segoe UI", Font.BOLD, 14));
        aboveCount.setForeground(Color.WHITE);
        abovePanel.add(aboveLabel);
        abovePanel.add(aboveCount);

        // เพิ่มทั้งหมดลงไปใน buttonPanel
        buttonPanel.add(noGasPanel);
        buttonPanel.add(belowPanel);
        buttonPanel.add(abovePanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
