package app;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        //หน้าหลักเด้อ
        setTitle("CAT DRILLING MENU");
        setSize(900, 600);
        setLocationRelativeTo(null); // เปิดกลางจอ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(97, 95, 95));

        // ===== หัวข้อ =====
        JLabel titleLabel = new JLabel("CAT DRILLING", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // ===== โลโก้ (ตรงกลาง) =====
        ImageIcon logoIcon = new ImageIcon("photo/logo.png");
        Image originalImage = logoIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(resizedImage), SwingConstants.CENTER);
        add(logoLabel, BorderLayout.CENTER);

        // ===== ปุ่มด้านขวา =====
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBackground(new Color(200, 200, 200, 60));
        buttonPanel.setPreferredSize(new Dimension(200, 0));

        // ฟอนต์ที่ใช้กับปุ่มทั้งหมด
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        // ปุ่ม START
        JButton btnStart = new JButton("START");
        btnStart.setFont(buttonFont);
        btnStart.setBackground(new Color(60, 120, 220));
        btnStart.setForeground(Color.WHITE);

        // ปุ่ม ABOUT
        JButton btnAbout = new JButton("ABOUT");
        btnAbout.setFont(buttonFont);
        btnAbout.setBackground(new Color(255, 200, 0));
        btnAbout.setForeground(Color.BLACK);

        // ปุ่ม EXIT
        JButton btnExit = new JButton("EXIT");
        btnExit.setFont(buttonFont);
        btnExit.setBackground(new Color(220, 60, 60));
        btnExit.setForeground(Color.WHITE);

        // ===== การทำงานของปุ่ม =====
        btnStart.addActionListener(e -> {
            new GasCalculatorFrame().setVisible(true);
            dispose(); // ปิดหน้าเมนู
        });

        btnAbout.addActionListener(e -> {
            new AboutDialog(this).setVisible(true);
        });

        btnExit.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnStart);
        buttonPanel.add(btnAbout);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.EAST);
    }
}
