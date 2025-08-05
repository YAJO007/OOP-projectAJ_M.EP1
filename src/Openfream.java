import javax.swing.*;
import java.awt.*;

public class Openfream {
    public static void main(String[] args) {
        Setframe();
    }

    public static void Setframe() {
        JFrame frame = new JFrame("CAT DRILLING");
        frame.setSize(1600, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.add(new MenuPanel());

        frame.setVisible(true);
    }

}
