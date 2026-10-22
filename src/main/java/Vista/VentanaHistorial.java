package Vista;

import Controlador.ResultadoController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorial {
    private final JFrame frame = new JFrame("Historial - Casino Black Cat");
    private final JTextArea txtHistorial = new JTextArea();

    private final ResultadoController resultadoController;

    public VentanaHistorial(SessionController sessionController) {
        this.resultadoController = new ResultadoController(sessionController);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new BorderLayout());

        txtHistorial.setEditable(false);

        frame.add(new JScrollPane(txtHistorial), BorderLayout.CENTER);

        actualizarDatos();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actualizarDatos() {
        List<String> historial = resultadoController.obtenerHistorial();

        if (historial.isEmpty()) {
            txtHistorial.setText("Aún no se han realizado apuestas.");
            return;
        }

        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < historial.size(); i++) {
            texto.append("Apuesta ").append(i + 1).append(":\n")
                    .append(historial.get(i)).append("\n\n");
        }
        txtHistorial.setText(texto.toString());
    }
}