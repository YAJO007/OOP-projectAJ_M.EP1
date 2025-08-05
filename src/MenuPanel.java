import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setLayout(null);
        setBounds(1150, 100, 350, 600);
        setBackground(new Color(0, 0, 0));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel label = new JLabel("MENU", SwingConstants.CENTER);
        label.setBounds(50, 30, 250, 60); // ใหญ่ขึ้น
        label.setFont(new Font("SansSerif", Font.BOLD, 36));
        add(label);

        add(Button.Setbutton("START", 75, 120, 200, 70));
        add(Button.Setbutton("ABOUT", 75, 220, 200, 70));
        add(Button.Setbutton("EXIT", 75, 320, 200, 70));
    }
}
