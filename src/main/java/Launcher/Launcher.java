package Launcher;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaLogin;
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

        SessionController session = new SessionController();
        RuletaController ruletaController = new RuletaController();
        VentanaLogin ventana = new VentanaLogin(session, ruletaController);
        ventana.mostrarVentana();
    }
}
