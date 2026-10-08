package Vista;

import Modelo.Ruleta;
import Modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JLabel lblMonto = new JLabel("Ingrese el motno deseado:");
    private final JTextField txtMonto = new JTextField();
    private final JLabel lblApuestas = new JLabel("Elija un tipo de apuesta:");
    private final JComboBox<TipoApuesta> cboApuestas =new JComboBox<>(TipoApuesta.values());
    private final JButton btnApostar = new JButton("Apostar");

    private final Ruleta ruleta = new Ruleta();

    public VentanaRuleta() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 1, 5, 5));

        btnApostar.addActionListener(e -> apostarRuleta());

        frame.add(lblMonto);
        frame.add(txtMonto);
        frame.add(lblApuestas);
        frame.add(cboApuestas);
        frame.add(btnApostar);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private boolean verificarMonto(String monto) {
        try {
            Integer.parseInt(monto);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private void apostarRuleta() {
        String monto = txtMonto.getText();
        TipoApuesta tipoApuesta = (TipoApuesta) cboApuestas.getSelectedItem();

        if (!verificarMonto(monto)) {
            JOptionPane.showMessageDialog(null, "El monto debe ser un número, intente denuevo.");
            return;
        }
        int montoUsuario = Integer.parseInt(monto);
        if (!ruleta.validarMonto(montoUsuario)) {
            JOptionPane.showMessageDialog(null, "El monto debe ser mayor que cero, intente denuevo.");
            return;
        }
        JOptionPane.showMessageDialog(null, ruleta.iniciarRonda(tipoApuesta, montoUsuario));
    }
}