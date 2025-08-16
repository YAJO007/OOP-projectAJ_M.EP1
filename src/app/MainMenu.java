package app;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("CAT DRILLING MENU");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 245, 250)); // พื้นหลังฟ้าอ่อน

        // ===== Title =====
        JLabel titleLabel = new JLabel("CAT DRILLING", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(30, 60, 120)); // ฟ้าเข้ม
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);

        // ===== Button Panel ทางขวา =====
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 50));
        buttonPanel.setBackground(new Color(153, 0, 255, 60));
        buttonPanel.setPreferredSize(new Dimension(200, 0)); // กำหนดความกว้างให้พอดี

        JButton btnStart = createRoundedButton("START", new Color(60, 120, 220));
        JButton btnAbout = createRoundedButton("ABOUT", new Color(255, 236, 0));
        JButton btnExit = createRoundedButton("EXIT", new Color(220, 60, 60));

        // Action
        btnStart.addActionListener(e -> {
            new GasCalculatorFrame().setVisible(true);
            dispose();
        });

        btnAbout.addActionListener(e -> new AboutDialog(this).setVisible(true));

        btnExit.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnStart);
        buttonPanel.add(btnAbout);
        buttonPanel.add(btnExit);

        // วาง Panel ทางขวา
        add(buttonPanel, BorderLayout.EAST);
    }

    // ===== เมธอดสร้างปุ่มมนๆ =====
    private JButton createRoundedButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        // ทำให้ปุ่มมีสีเต็มและมุมมน
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBorder(new LineBorder(bgColor, 1, true)); // มุมมน

        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(bgColor, 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        // Hover effect
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
}
