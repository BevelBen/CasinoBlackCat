import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaRuleta {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");

    public VentanaRuleta() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 1, 5 , 5));
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
