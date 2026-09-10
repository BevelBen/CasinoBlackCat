import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaRuleta {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
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

    /**
     * Valida el monto ingresado y, de ser correcto, inicia una ronda
     * de la ruleta con el tipo de apuesta seleccionado.
     *
     * @param apuesta tipo de apuesta elegida (R/N/P/I).
     */
    private void apostarRuleta(char apuesta) {
        int monto;

        try {
            monto = Integer.parseInt(txtMonto.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame,
                    "Ingrese un monto numérico válido.",
                    "Monto inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ruleta.validarMonto(monto)) {
            JOptionPane.showMessageDialog(frame,
                    "El monto debe ser mayor que cero.",
                    "Monto inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        ruleta.iniciarRonda(apuesta, monto);

        if (!ruleta.ultimoAviso.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    ruleta.ultimoAviso,
                    "Historial lleno",
                    JOptionPane.WARNING_MESSAGE);
        }

        JOptionPane.showMessageDialog(frame,
                ruleta.ultimoResultado,
                "Resultado de la ronda",
                ruleta.ultimoAcierto ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);

        txtMonto.setText("");
    }

}