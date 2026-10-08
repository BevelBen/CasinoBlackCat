package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaPerfil {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Perfil - Casino Black Cat");
    private final JLabel lblNombre = new JLabel("Nombre de usuario:");
    private final JTextField txtNombre = new JTextField();
    private final JButton btnGuardarNombre = new JButton("Guardar nombre");
    private final JLabel lblSaldo = new JLabel();
    private final JTextField txtRecarga = new JTextField();
    private final JButton btnRecargar = new JButton("Recargar saldo");

    private final SessionController session;
    private final RuletaController ruletaController;

    public VentanaPerfil(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setLayout(new GridLayout(6, 1, 5, 5));

        btnGuardarNombre.addActionListener(e -> guardarNombre());
        btnRecargar.addActionListener(e -> recargar());

        frame.add(lblNombre);
        frame.add(txtNombre);
        frame.add(btnGuardarNombre);
        frame.add(lblSaldo);
        frame.add(txtRecarga);
        frame.add(btnRecargar);

        txtNombre.setText(session.getNombreUsuario());
        actualizarSaldo();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void actualizarSaldo() {
        lblSaldo.setText("Saldo actual: $" + ruletaController.getSaldo() + " | Monto a recargar:");
    }

    private void guardarNombre() {
        try {
            session.cambiarNombre(txtNombre.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Nombre no válido", JOptionPane.WARNING_MESSAGE);
            txtNombre.setText(session.getNombreUsuario());
            return;
        }
        txtNombre.setText(session.getNombreUsuario());
        JOptionPane.showMessageDialog(frame, "Nombre actualizado.");
    }

    private void recargar() {
        try {
            ruletaController.depositar(txtRecarga.getText());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Recarga no válida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        txtRecarga.setText("");
        actualizarSaldo();
    }
}
