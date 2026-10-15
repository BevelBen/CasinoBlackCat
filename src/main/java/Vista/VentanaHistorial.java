package Vista;

import Controlador.RuletaController;
import Modelo.ResultadoApuesta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorial {
    private final JFrame frame = new JFrame("Historial - Casino Black Cat");
    private final JTextArea txtHistorial = new JTextArea();

    private final RuletaController ruletaController;

    public VentanaHistorial(RuletaController ruletaController) {
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new BorderLayout());

        txtHistorial.setEditable(false);

        frame.add(new JScrollPane(txtHistorial), BorderLayout.CENTER);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}