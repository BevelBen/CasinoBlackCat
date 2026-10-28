package Vista;

import Controlador.RuletaController;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JLabel lblSaldo = new JLabel();
    private final JTextField txtMonto = new JTextField();
    private final JComboBox<String> cboApuestas = new JComboBox<>();

    private final RuletaController ruletaController;

    public VentanaRuleta(RuletaController ruletaController) {
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(6, 1, 5, 5));

        JButton btnApostar = new JButton("Apostar");
        btnApostar.addActionListener(e -> apostarRuleta());

        cboApuestas.addItem("Negro");
        cboApuestas.addItem("Rojo");
        cboApuestas.addItem("Par");
        cboApuestas.addItem("Impar");

        frame.add(lblSaldo);
        JLabel lblMonto = new JLabel("Ingrese el monto deseado:");
        frame.add(lblMonto);
        frame.add(txtMonto);
        JLabel lblApuestas = new JLabel("Elija un tipo de apuesta:");
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
        String tipo = (String) cboApuestas.getSelectedItem();

        try {
            JOptionPane.showMessageDialog(frame, ruletaController.realizarApuesta(tipo, txtMonto.getText()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Apuesta no válida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        actualizarEstado();
    }
}