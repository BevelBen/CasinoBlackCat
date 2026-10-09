package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Menú - Casino Black Cat");
    private final JLabel lblBienvenida = new JLabel();
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
        btnSalir.addActionListener(e -> cerrarSeesion());

        frame.add(lblBienvenida);
        frame.add(btnJugar);
        frame.add(btnPerfil);
        frame.add(btnSalir);

        actualizarDatos();
    }

    private void cerrarSeesion() {
        session.cerrarSesion();
        new VentanaLogin(session, ruletaController).mostrarVentana();
        frame.dispose();
    }

    public void actualizarDatos() {
        lblBienvenida.setText("Bienvenido, " + session.getNombreUsuario());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}