package app;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame parent) {
        super(parent, "เกี่ยวกับโปรแกรม", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JTextArea textArea = new JTextArea(
                "โปรแกรม Jewel Suite\n" +
                "เวอร์ชัน: 1.0\n" +
                "ผู้พัฒนา: ทีมของคุณ\n" +
                "ใช้สำหรับจำลองการขุดเจาะแก๊ส"
        );
        textArea.setEditable(false);
        textArea.setBackground(new Color(240, 240, 240));

        add(textArea, BorderLayout.CENTER);
    }
}
