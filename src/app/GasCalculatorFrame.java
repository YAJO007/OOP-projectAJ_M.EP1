package app;

import model.GasCalculator;
import model.GasDataLoader;
import util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class GasCalculatorFrame extends JFrame {
    private JTable table;
    private JTextField fluidContactField;
    private GasCalculator calculator;
    private GasDataLoader loader;

    public GasCalculatorFrame() {
        setTitle("Jewel Suite - Gas Volume Calculator");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calculator = new GasCalculator();
        loader = new GasDataLoader();

        JPanel topPanel = new JPanel();
        JButton btnLoad = new JButton("โหลดไฟล์ dept.txt");
        fluidContactField = new JTextField("2.5", 5);
        JButton btnCalc = new JButton("คำนวณ");

        topPanel.add(btnLoad);
        topPanel.add(new JLabel("Fluid Contact (km):"));
        topPanel.add(fluidContactField);
        topPanel.add(btnCalc);

        add(topPanel, BorderLayout.NORTH);

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnLoad.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                loader.load(file);
                calculator.setBaseHorizon(loader.getData());
                JOptionPane.showMessageDialog(this, "โหลดไฟล์เรียบร้อย");
            }
        });

        btnCalc.addActionListener(e -> {
            try {
                double fluidContact = Double.parseDouble(fluidContactField.getText()) * 1000; // km → m
                double[][] percent = calculator.calculateGas(fluidContact);
                showTable(percent);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "กรุณากรอกตัวเลขถูกต้อง");
            }
        });
    }

    private void showTable(double[][] percent) {
        int rows = percent.length;
        int cols = percent[0].length;

        DefaultTableModel model = new DefaultTableModel(rows, cols);
        table.setModel(model);

        table.setDefaultRenderer(Object.class, new TableColorRenderer());

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                table.setValueAt(percent[r][c], r, c);
            }
        }
    }
}
