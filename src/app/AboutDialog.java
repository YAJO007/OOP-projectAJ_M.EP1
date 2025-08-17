package app;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "About", true);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // ====== Main Panel ======
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(createMemberCard
                (
                "Valentin Nokiara",
                "ID: 67011212135",
                "Position: Programmer",
                "photo/walen1.jpg"
        ));

        mainPanel.add(createMemberCard
                (
                "Thanatut Saengchan",
                "ID: 67011212027",
                "Position: Programmer",
                "photo/dunk.jpg"
        ));

        mainPanel.add(createMemberCard
                (
                "Saranon Suaksanlert",
                "ID: 67011212137",
                "Position: Programmer",
                "photo/toe.jpg"
        ));
        add(mainPanel, BorderLayout.CENTER);
    }

    // ===== ฟังก์ชันสร้าง Card สมาชิก =====
    private JPanel createMemberCard(String name, String id, String role, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(200, 250)); // กำหนดขนาดการ์ด
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        panel.setBackground(Color.WHITE);

        // โหลดรูป
        JLabel imgLabel;
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imgLabel = new JLabel(new ImageIcon(scaled));
        } catch (Exception e) {
            imgLabel = new JLabel("[No Image]");
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ข้อความ
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel idLabel = new JLabel(id, SwingConstants.CENTER);
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel roleLabel = new JLabel(role, SwingConstants.CENTER);
        roleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        roleLabel.setForeground(Color.DARK_GRAY);
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // เพิ่มเข้าการ์ด
        panel.add(imgLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(nameLabel);
        panel.add(idLabel);
        panel.add(roleLabel);

        return panel;
    }
}
