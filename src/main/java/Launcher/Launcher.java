package Launcher;

import Controlador.ResultadoController;
import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.Ruleta;
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
        RuletaController ruletaController = new RuletaController(session);
        ResultadoController resultadoController = new ResultadoController(session);
        VentanaLogin ventana = new VentanaLogin(session, ruletaController, resultadoController);
        ventana.mostrarVentana();
    }
}
