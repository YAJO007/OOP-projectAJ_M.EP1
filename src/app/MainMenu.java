package app;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Jewel Suite - Main Menu");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Jewel Suite", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton btnStart = new JButton("Start");
        JButton btnAbout = new JButton("เกี่ยวกับ");
        JButton btnExit = new JButton("Exit");

        btnStart.addActionListener(e -> {
            new GasCalculatorFrame().setVisible(true);
            dispose();
        });

        btnAbout.addActionListener(e -> new AboutDialog(this).setVisible(true));

        btnExit.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnStart);
        buttonPanel.add(btnAbout);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
