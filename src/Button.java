import javax.swing.*;
import java.awt.*;

public class Button {
    public static JButton Setbutton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(230, 230, 230));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("SansSerif", Font.PLAIN, 26));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }
}
