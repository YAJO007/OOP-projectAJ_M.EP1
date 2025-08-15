import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;

import app.MainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatSolarizedLightIJTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
