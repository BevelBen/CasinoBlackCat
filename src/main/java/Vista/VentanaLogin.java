package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin {
    // --- Componentes de la interfaz gráfica ---
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrarse = new JButton("Registrarse");

    private SessionController session;
    private RuletaController ruletaController;

    /**
     * Constructor que inicializa la ventana de inicio de sesión.
     * Configura sus componentes y eventos.
     */
    public VentanaLogin(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 1, 5, 5));

        btnIngresar.addActionListener(e -> login());
        btnRegistrarse.addActionListener(e -> registrarse());

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnIngresar);
        frame.add(btnRegistrarse);
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

        if (!session.verificarUsuario(usuarioIngresado)) {
            JOptionPane.showMessageDialog(frame, "El usuario que intento buscar no se encuentra en la base de datos, intente denuevo.", "Usuario No Encontrado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!session.iniciarSesion(usuarioIngresado, claveIngresada)) {
            JOptionPane.showMessageDialog(frame, "La contraseña ingresada no es correcta, intente denuevo.", "Contraseña Equivocada", JOptionPane.WARNING_MESSAGE);
            return;
        }
        frame.dispose();
        VentanaMenu ventanaMenu = new VentanaMenu(session, ruletaController);
        ventanaMenu.mostrarVentana();
    }

    private void registrarse() {
        frame.dispose();
        VentanaRegistro ventanaRegistro = new VentanaRegistro(session, ruletaController);
        ventanaRegistro.mostrarVentana();
    }
}