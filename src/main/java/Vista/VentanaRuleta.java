package Vista;

import Controlador.RuletaController;
import Modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JLabel lblSaldo = new JLabel();
    private final JLabel lblMonto = new JLabel("Ingrese el monto deseado:");
    private final JTextField txtMonto = new JTextField();
    private final JLabel lblApuestas = new JLabel("Elija un tipo de apuesta:");
    private final JComboBox<TipoApuesta> cboApuestas =new JComboBox<>(TipoApuesta.values());
    private final JButton btnApostar = new JButton("Apostar");

    private final RuletaController ruletaController;

    public VentanaRuleta(RuletaController ruletaController) {
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(6, 1, 5, 5));

        btnApostar.addActionListener(e -> apostarRuleta());

        frame.add(lblSaldo);
        frame.add(lblMonto);
        frame.add(txtMonto);
        frame.add(lblApuestas);
        frame.add(cboApuestas);
        frame.add(btnApostar);

        actualizarEstado();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actualizarEstado() {
        lblSaldo.setText("Estado actual: " + ruletaController.getSaldo());
    }

    private void apostarRuleta() {
        TipoApuesta tipoApuesta = (TipoApuesta) cboApuestas.getSelectedItem();

        try {
            JOptionPane.showMessageDialog(frame, ruletaController.realizarApuesta(tipoApuesta, txtMonto.getText()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Apuesta no válida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        actualizarEstado();
    }
}