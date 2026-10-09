package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaMenu {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JLabel lblBienvenida = new JLabel();
    private final JLabel lblSaldo = new JLabel();
    private final JButton btnJugar = new JButton("Jugar a la ruleta");
    private final JButton btnPerfil = new JButton("Mi perfil");
    private final JButton btnSalir = new JButton("Salir");

    private final SessionController session;
    private final RuletaController ruletaController;

    public VentanaMenu(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 260);
        frame.setLayout(new GridLayout(5, 1, 5, 5));

        btnJugar.addActionListener(e ->
                new VentanaRuleta(ruletaController).mostrarVentana());
        btnPerfil.addActionListener(e ->
                new VentanaPerfil(session, ruletaController).mostrarVentana());
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
        frame.add(btnPerfil);
        frame.add(btnSalir);

        actualizarDatos();
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        new VentanaLogin(session, ruletaController).mostrarVentana();
        frame.dispose();
    }

    public void actualizarDatos() {
        lblBienvenida.setText("Bienvenido, " + session.getNombreUsuario());
        lblSaldo.setText("Saldo actual: $" + ruletaController.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}