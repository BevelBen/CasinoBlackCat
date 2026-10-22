package Vista;

import Controlador.ResultadoController;
import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();

    private final SessionController sessionController;
    private final RuletaController ruletaController;
    private final ResultadoController resultadoController;

    public VentanaRegistro(SessionController sessionController, RuletaController ruletaController, ResultadoController resultadoController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;
        this.resultadoController = resultadoController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 420);
        frame.setLayout(new GridLayout(10, 1, 5, 5));

        JButton btnRegistrar = new JButton("Crear cuenta");
        btnRegistrar.addActionListener(e -> registrar());
        JButton btnVolver = new JButton("Volver al login");
        btnVolver.addActionListener(e -> volver());

        JLabel lblUsuario = new JLabel("Usuario:");
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        JLabel lblNombre = new JLabel("Nombre:");
        frame.add(lblNombre);
        frame.add(txtNombre);
        JLabel lblClave = new JLabel("Clave:");
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
        try {
            sessionController.registrarUsuario(txtUsuario.getText().trim(), new String(txtClave.getPassword()), txtNombre.getText().trim());
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Registro no válido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(frame, "Cuenta creada. Ya puedes iniciar sesión.");
        volver();
    }

    private void volver() {
        frame.dispose();
        new VentanaLogin(sessionController, ruletaController, resultadoController).mostrarVentana();
    }
}
