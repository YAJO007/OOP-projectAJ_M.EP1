package app;

import model.GasCalculator;
import model.GasDataLoader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;

public class GasCalculatorFrame extends JFrame {

    private JTable table;
    private JTextField fluidContactField;
    private GasCalculator calculator;
    private GasDataLoader loader;

    public GasCalculatorFrame() {
        setTitle("CAT DRILLING");
        setSize(1400, 850); // ขนาดใหญ่ขึ้น
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calculator = new GasCalculator();
        loader = new GasDataLoader();

        // ===== Main Panel =====
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== Left Control Panel =====
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(320, 0));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // ✅ ปุ่มที่เท่ากันทั้งหมด
        JButton btnLoad   = createFixedButton("Open File", new Color(140, 0, 255), Color.WHITE);
        JButton btnChange = createFixedButton("Change",    new Color(40, 167, 69), Color.WHITE);
        JButton btnReset  = createFixedButton("RESET",     new Color(220, 53, 69), Color.WHITE);
        JButton btnCast   = createFixedButton("SUMMARY",   new Color(255, 193, 7), Color.WHITE);

        JLabel fluidLabel = new JLabel("Fluid Enter(M)");
        fluidLabel.setForeground(Color.DARK_GRAY);
        fluidLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        fluidContactField = new JTextField("2.5");
        fluidContactField.setMaximumSize(new Dimension(200, 40));
        fluidContactField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        fluidContactField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

        // Legend Panel
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBackground(Color.WHITE);
        legendPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Legend", 0, 0, new Font("Segoe UI", Font.BOLD, 14)
        ));
        legendPanel.add(createLegendItem("No Gas", new Color(255, 102, 102)));
        legendPanel.add(Box.createVerticalStrut(12));
        legendPanel.add(createLegendItem("Gas Below 50%", new Color(255, 223, 128)));
        legendPanel.add(Box.createVerticalStrut(12));
        legendPanel.add(createLegendItem("Gas Above 50%", new Color(144, 238, 144)));

        // Add components to left panel
        leftPanel.add(btnLoad);
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(fluidLabel);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(fluidContactField);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(btnChange);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(btnReset);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(btnCast);
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(legendPanel);

        // ===== Center Table Panel =====
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(38);
        table.setGridColor(new Color(220, 220, 220));
        table.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // ===== Right Panel =====
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(180, 0));
        rightPanel.setBackground(Color.WHITE);

        JLabel catLabel = new JLabel("CAT DRILLING", SwingConstants.CENTER);
        catLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        catLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        rightPanel.add(catLabel, BorderLayout.NORTH);

        // ===== Layout Assembly =====
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel);

        // ===== Button Actions =====
        btnLoad.addActionListener(e -> openTxtFile());
        btnChange.addActionListener(e -> calculateGas());
        btnReset.addActionListener(e -> resetTable());
        btnCast.addActionListener(e -> {
            SummaryDialog dialog = new SummaryDialog(this, table);
            dialog.setVisible(true);
        });
    }

    // ===== ปุ่มสวย + เท่ากัน + Hover Effect =====
    private JButton createFixedButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(textColor);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ✅ ขนาดคงที่ทุกปุ่ม
        Dimension fixedSize = new Dimension(200, 45);
        button.setPreferredSize(fixedSize);
        button.setMaximumSize(fixedSize);
        button.setMinimumSize(fixedSize);

        // Hover Effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private JPanel createLegendItem(String text, Color color) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panel.setBackground(Color.WHITE);

        JLabel colorBox = new JLabel();
        colorBox.setOpaque(true);
        colorBox.setBackground(color);
        colorBox.setPreferredSize(new Dimension(35, 20));
        colorBox.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        panel.add(colorBox);
        panel.add(textLabel);
        return panel;
    }

    private void openTxtFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            loader.load(file);
            calculator.setBaseHorizon(loader.getData());
            JOptionPane.showMessageDialog(this, "Loaded: " + file.getName());
        }
    }

    private void calculateGas() {
        try {
            double fluidContact = Double.parseDouble(fluidContactField.getText()) * 1000;
            double[][] percent = calculator.calculateGas(fluidContact);
            showTable(percent);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "กรุณากรอกตัวเลขถูกต้อง");
        }
    }

    private void resetTable() {
        table.setModel(new DefaultTableModel());
    }

    private void showTable(double[][] percent) {
        int rows = percent.length;
        int cols = percent[0].length;
        DefaultTableModel model = new DefaultTableModel(rows, cols);
        table.setModel(model);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
                if (value instanceof Number) {
                    double val = ((Number) value).doubleValue();
                    if (val == 0) c.setBackground(new Color(255, 102, 102));
                    else if (val < 50) c.setBackground(new Color(255, 223, 128));
                    else c.setBackground(new Color(144, 238, 144));
                } else {
                    c.setBackground(Color.WHITE);
                }
                setForeground(Color.BLACK);
                setHorizontalAlignment(SwingConstants.CENTER);
                setFont(new Font("Segoe UI", Font.BOLD, 16));
                return c;
            }
        });

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                table.setValueAt(percent[r][c], r, c);
    }
}
