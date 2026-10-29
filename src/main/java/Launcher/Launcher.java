package Launcher;

import Controlador.ResultadoController;
import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.PersistenciaUsuarios;
import Modelo.UsuarioManager;
import Vista.VentanaLogin;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.nio.file.Path;

public class Launcher {
    void main() {
        try { // Apariencia con FlatLaf
            UIManager.setLookAndFeel(
                    new FlatDarkLaf()
            );
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        UsuarioManager usuarioManager;
        try {
            usuarioManager = new UsuarioManager(new PersistenciaUsuarios(Path.of("data", "usuarios.txt")));
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al cargar los datos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SessionController sessionController = new SessionController(usuarioManager);
        RuletaController ruletaController = new RuletaController(sessionController);
        ResultadoController resultadoController = new ResultadoController(sessionController);
        new VentanaLogin(sessionController, ruletaController, resultadoController).mostrarVentana();
    }
}
