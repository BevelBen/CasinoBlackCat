package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JLabel lblNombre = new JLabel("Nombre:");
    private final JTextField txtNombre = new JTextField();
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnRegistrar = new JButton("Crear cuenta");
    private final JButton btnVolver = new JButton("Volver al login");

    private final SessionController session;
    private final RuletaController ruletaController;

    public VentanaRegistro(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 420);
        frame.setLayout(new GridLayout(10, 1, 5, 5));

        btnRegistrar.addActionListener(e -> registrar());
        btnVolver.addActionListener(e -> volver());

        frame.add(lblNombre);
        frame.add(txtNombre);
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnRegistrar);
        frame.add(btnVolver);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrar() {
        String clave = new String(txtClave.getPassword());

        try {
            session.registrarUsuario(txtUsuario.getText().trim(), clave, txtNombre.getText().trim());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Registro no válido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(frame, "Cuenta creada. Ya puedes iniciar sesión.");
        volver();
    }

    private void volver() {
        frame.dispose();
        new VentanaLogin(session, ruletaController).mostrarVentana();
    }
}
