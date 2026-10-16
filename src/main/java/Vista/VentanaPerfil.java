package Vista;

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

    private final SessionController sessionController;

    public VentanaPerfil(SessionController sessionController) {
        this.sessionController = sessionController;

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

        txtNombre.setText(sessionController.getNombreUsuario());
        actualizarSaldo();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void actualizarSaldo() {
        lblSaldo.setText("Saldo actual: $" + sessionController.getSaldo() + " | Monto a recargar:");
    }

    private void guardarNombre() {
        try {
            sessionController.cambiarNombre(txtNombre.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Nombre no válido", JOptionPane.WARNING_MESSAGE);
            txtNombre.setText(sessionController.getNombreUsuario());
            return;
        }
        txtNombre.setText(sessionController.getNombreUsuario());
        JOptionPane.showMessageDialog(frame, "Nombre actualizado.");
    }

    private void recargar() {
        try {
            sessionController.depositar(txtRecarga.getText());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Recarga no válida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        txtRecarga.setText("");
        actualizarSaldo();
    }
}
