import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDraculaIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Openfream().Setframe();
        });
        MenuPanel menu = new MenuPanel();
    }
}
