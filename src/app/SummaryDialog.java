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
                String.format(
                        "Gas Volume Summary\n\n" +
                                "Total Cells: %d\n" +
                                "No Gas: %d cells (%.2f%%)\n" +
                                "Gas Below 50%%: %d cells (%.2f%%)\n" +
                                "Gas Above 50%%: %d cells (%.2f%%)",
                        total, noGas, pNoGas, gasBelow50, pBelow, gasAbove50, pAbove
                )
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

        JButton redButton = new JButton(String.format("<html>No Gas<br>%d (%.2f%%)</html>", noGas, pNoGas));
        redButton.setBackground(Color.RED);
        redButton.setForeground(Color.WHITE);
        redButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton orangeButton = new JButton(String.format("<html>Gas < 50%<br>%d (%.2f%%)</html>", gasBelow50, pBelow));
        orangeButton.setBackground(Color.ORANGE);
        orangeButton.setForeground(Color.BLACK);
        orangeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton greenButton = new JButton(String.format("<html>Gas > 50%<br>%d (%.2f%%)</html>", gasAbove50, pAbove));
        greenButton.setBackground(Color.GREEN.darker());
        greenButton.setForeground(Color.WHITE);
        greenButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        buttonPanel.add(redButton);
        buttonPanel.add(orangeButton);
        buttonPanel.add(greenButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
