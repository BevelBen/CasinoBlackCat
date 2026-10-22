package Vista;

import Controlador.ResultadoController;
import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JLabel lblBienvenida = new JLabel();
    private final JLabel lblSaldo = new JLabel();

    private final SessionController sessionController;
    private final RuletaController ruletaController;
    private final ResultadoController resultadoController;

    public VentanaMenu(SessionController sessionController, RuletaController ruletaController, ResultadoController resultadoController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;
        this.resultadoController = resultadoController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 260);
        frame.setLayout(new GridLayout(5, 1, 5, 5));

        JButton btnJugar = new JButton("Jugar a la ruleta");
        btnJugar.addActionListener(e ->
                new VentanaRuleta(ruletaController).mostrarVentana());
        JButton btnHistorial = new JButton("Ver el historial");
        btnHistorial.addActionListener(e ->
                new VentanaHistorial(resultadoController).mostrarVentana());
        JButton btnPerfil = new JButton("Mi perfil");
        btnPerfil.addActionListener(e ->
                 new VentanaPerfil(sessionController).mostrarVentana());
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> cerrarSesion());

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                actualizarDatos();
            }
        });

        frame.add(lblBienvenida);
        frame.add(lblSaldo);
        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnPerfil);
        frame.add(btnSalir);

        actualizarDatos();
    }

    private void cerrarSesion() {
        sessionController.cerrarSesion();
        new VentanaLogin(sessionController, ruletaController, resultadoController).mostrarVentana();
        frame.dispose();
    }

    public void actualizarDatos() {
        lblBienvenida.setText("Bienvenido, " + sessionController.getNombreUsuario());
        lblSaldo.setText("Saldo actual: $" + sessionController.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}