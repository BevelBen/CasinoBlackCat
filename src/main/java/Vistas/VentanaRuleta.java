package Vistas;

import Servicios.Ruleta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Servicios.Ruleta.Ruleta - Casino Black Cat");
    private final JLabel lblMonto = new JLabel("Ingrese el motno deseado:");
    private final JTextField txtMonto = new JTextField();
    private final JLabel lblApuestas = new JLabel("Elija un tipo de apuesta:");
    private final JButton btnRojo = new JButton("Rojo");
    private final JButton btnNegro = new JButton("Negro");
    private final JButton btnPar = new JButton("Par");
    private final JButton btnImpar = new JButton("Impar");

    private final Ruleta ruleta = new Ruleta();

    public VentanaRuleta() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 1, 5 , 5));

        btnRojo.addActionListener(e -> apostarRuleta(Ruleta.OPCION_ROJO));
        btnNegro.addActionListener(e -> apostarRuleta(Ruleta.OPCION_NEGRO));
        btnPar.addActionListener(e -> apostarRuleta(Ruleta.OPCION_PAR));
        btnImpar.addActionListener(e -> apostarRuleta(Ruleta.OPCION_IMPAR));

        frame.add(lblMonto);
        frame.add(txtMonto);
        frame.add(lblApuestas);
        frame.add(btnRojo);
        frame.add(btnNegro);
        frame.add(btnPar);
        frame.add(btnImpar);
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

    private void apostarRuleta(char opcion) {
        String monto = txtMonto.getText();
        if (!verificarMonto(monto)) {
            JOptionPane.showMessageDialog(null, "El monto debe ser un número, intente denuevo.");
            return;
        }
        int montoUsuario = Integer.parseInt(monto);
        if (ruleta.validarMonto(montoUsuario)) {
            JOptionPane.showMessageDialog(null, ruleta.iniciarRonda(opcion, montoUsuario));
            return;
        }
        JOptionPane.showMessageDialog(null, "El monto debe ser mayor que cero, intente denuevo.");
    }

}