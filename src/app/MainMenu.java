package app;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setupFrame();
        addTitle();
        addButtonPanel();
    }

    // ===== ตั้งค่าหน้าต่างหลัก =====
    private void setupFrame() {
        setTitle("CAT DRILLING MENU");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(211, 172, 246)); // ฟ้าอ่อน
    }

    // ===== เพิ่มหัวข้อ =====
    private void addTitle() {
        JLabel titleLabel = new JLabel("CAT DRILLING", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 0, 0)); // ฟ้าเข้ม
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);
    }

    // ===== Panel ปุ่มด้านขวา =====
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 50));
        buttonPanel.setBackground(new Color(153, 0, 255, 60));
        buttonPanel.setPreferredSize(new Dimension(200, 0));

        // ปุ่ม
        JButton btnStart = createButton("START", new Color(60, 120, 220));
        JButton btnAbout = createButton("ABOUT", new Color(255, 200, 0));
        JButton btnExit = createButton("EXIT", new Color(220, 60, 60));

        // Action ของแต่ละปุ่ม
        btnStart.addActionListener(e -> openGasCalculator());
        btnAbout.addActionListener(e -> showAboutDialog());
        btnExit.addActionListener(e -> System.exit(0));

        // ใส่ปุ่มลง panel
        buttonPanel.add(btnStart);
        buttonPanel.add(btnAbout);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.EAST);
    }

    // ===== Action =====
    private void openGasCalculator() {
        new GasCalculatorFrame().setVisible(true);
        dispose();
    }

    private void showAboutDialog() {
        new AboutDialog(this).setVisible(true);
    }

    // ===== สร้างปุ่มพร้อมสีพื้นหลัง + hover effect =====
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(bgColor, 1, true), // ขอบมน
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // ขยาย padding
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
    //โลโกนะ
    /*private void addTitle() {
        // โหลดรูปภาพโลโก้
        ImageIcon logoIcon = new ImageIcon("path/to/your/logo.png"); // ใส่ path ของไฟล์โลโก้
        JLabel logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);

        // หัวข้อ
        JLabel titleLabel = new JLabel("CAT DRILLING", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Panel สำหรับโลโก้ + หัวข้อ
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(211, 172, 246));
        titlePanel.add(logoLabel, BorderLayout.CENTER);
        titlePanel.add(titleLabel, BorderLayout.SOUTH);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        add(titlePanel, BorderLayout.NORTH);
    }*/

}
