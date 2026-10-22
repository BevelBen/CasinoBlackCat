package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaLogin;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Launcher {
    void main() {

        // APARIENCIA CON FLATLAF
        try {
            UIManager.setLookAndFeel(
                    new FlatDarkLaf()
            );
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SessionController sessionController = new SessionController();
        RuletaController ruletaController = new RuletaController(sessionController);
        new VentanaLogin(sessionController, ruletaController).mostrarVentana();
    }
}
