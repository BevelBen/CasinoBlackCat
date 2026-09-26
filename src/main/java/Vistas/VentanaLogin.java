package Vistas;

import Controladores.GestorUsuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");

    private GestorUsuario gestorUsuario = new GestorUsuario();

    /**
     * Constructor que inicializa la ventana de inicio de sesión.
     * Configura sus componentes y eventos.
     */
    public VentanaLogin() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 1, 5, 5));

        btnIngresar.addActionListener(e -> login());

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnIngresar);
    }

    /**
     * Muestra la ventana en pantalla.
     * Debe centrarla y hacerla visible.
     */
    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Gestiona el inicio de sesión al presionar el botón.
     * Debe validar las credenciales ingresadas y abrir la siguiente
     * ventana o mostrar un mensaje de error.
     */
    private void login() {
        String usuarioIngresado = txtUsuario.getText();
        char[] arrayClave = txtClave.getPassword();
        String claveIngresada = new String(arrayClave);

        if (!gestorUsuario.verificarUsuario(usuarioIngresado)) {
            JOptionPane.showMessageDialog(frame, "El usuario que intento buscar no se encuentra en la base de datos, intente denuevo.", "Usuario No Encontrado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!gestorUsuario.verificarClave(usuarioIngresado, claveIngresada)) {
            JOptionPane.showMessageDialog(frame, "La contraseña ingresada no es correcta, intente denuevo.", "Contraseña Equivocada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        frame.dispose();
        VentanaRuleta ventanaRuleta = new VentanaRuleta();
        ventanaRuleta.mostrarVentana();
    }
}