package app;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "About", true);
        setSize(800, 500);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        mainPanel.add(createMemberCard(
                "Valentin Nokiara",
                "ID: 67011212135",
                "position: Programmer",
                "photo/walen1.jpg"
        ));

        mainPanel.add(createMemberCard(
                "Thanatut Saengchan",
                "ID: 67011212027",
                "position: Programmer",
                "photo/dunk.jpg"
        ));

        mainPanel.add(createMemberCard(
                "Saranon suaksanlert",
                "ID: 67011212137",
                "position: Programmer",
                "photo/toe.jpg"
        ));

        add(mainPanel, BorderLayout.CENTER);
    }

    // 🔹 ฟังก์ชันสร้าง Card สมาชิกแต่ละคน
    private JPanel createMemberCard(String name, String id, String role, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setBackground(new Color(250, 250, 250));

        JLabel imgLabel = new JLabel();
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgLabel.setPreferredSize(new Dimension(50, 500));

        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            Image scaledImg = getScaledImage(img, 200, 200); // 🔹 ใช้ฟังก์ชัน Resize แบบชัด
            imgLabel.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            imgLabel.setText("[No Image]");
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel idLabel = new JLabel(id, SwingConstants.CENTER);
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel roleLabel = new JLabel(role, SwingConstants.CENTER);
        roleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        roleLabel.setForeground(new Color(70, 70, 70));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(imgLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(nameLabel);
        panel.add(idLabel);
        panel.add(roleLabel);

        return panel;
    }

    // 🔹 ฟังก์ชัน Resize รูปแบบคุณภาพสูง
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
