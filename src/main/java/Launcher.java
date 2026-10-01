import Vistas.VentanaLogin;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Launcher {
    public void main(String[] args) {

        // APARIENCIA CON FLATLAF
        try {
            UIManager.setLookAndFeel(
                    new FlatDarkLaf()
            );
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        VentanaLogin ventana = new VentanaLogin();
        ventana.mostrarVentana();
    }
}
