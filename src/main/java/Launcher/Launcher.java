package Launcher;

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

        new VentanaLogin(new SessionController()).mostrarVentana();
    }
}
