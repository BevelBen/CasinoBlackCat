package Controlador;

import Modelo.Resultado;
import Modelo.Usuario;
import Modelo.UsuarioManager;

import java.util.List;

public class SessionController {
    private Usuario usuarioActual;
    private final UsuarioManager usuarioManager;

    public SessionController() {
        this(new UsuarioManager());
    }

    public SessionController(UsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }

    /**
     * Verifica que exista cierto username.
     * @param username el username que se desea verificar.
     * @return true en caso de que exista, false en caso contrario.
     */
    public boolean verificarUsuario(String username) {
        return usuarioManager.verificarUsuario(username);
    }

    /**
     * Agrega un nuevo usuario a la lista de usuarios.
     * @param username el username del usuario.
     * @param clave la clave del usuario.
     * @param nombre el nombre del usuario.
     */
    public void registrarUsuario(String username, String clave, String nombre) {
        if (usuarioManager.verificarUsuario(username)) {
            throw new IllegalStateException("El usuario ya existe.");
        }
        usuarioManager.agregarUsuario(username, clave, nombre);
    }

    /**
     * Verifica las credenciales ingresadas, si existe el usuario y la clave es correcta.
     * @param username username del usuario.
     * @param clave contraseña del usuario.
     * @return true en caso de que las credenciales sean correctas, false en caso contrario.
     */
    public boolean iniciarSesion(String username, String clave) {
        if (usuarioManager.validarCredenciales(username, clave)) {
            usuarioActual = usuarioManager.buscarUsuario(username);
            return true;
        }
        return false;
    }

    /**
     * Cambia el nombre del usuario actual.
     * @param nombre el nombre al cual cambiarlo.
     */
    public void cambiarNombre(String nombre) {
        if (!hayUsuario()) {
            throw new IllegalStateException("No hay una sesión.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }
        usuarioActual.setNombre(nombre);
        usuarioManager.guardar();
    }

    /**
     * @return la cantidad del saldo del usuario actual.
     */
    public int getSaldo() {
        if (usuarioActual == null) {
            throw new IllegalStateException("No hay una sesión.");
        }
        return usuarioActual.getSaldo();
    }

    /**
     * Cambia el saldo del usuario actual.
     * @param saldo cantidad a la cual cambiar el saldo.
     */
    public void setSaldo(int saldo) {
        if (usuarioActual == null) {
            throw new IllegalStateException("No hay una sesión.");
        }
        usuarioActual.setSaldo(saldo);
        usuarioManager.guardar();
    }

    /**
     * Agrega una cantidad entera al saldo del usuario actual.
     * @param monto el monto que se desea agregar.
     */
    public void depositar(int monto) {
        if (usuarioActual == null) {
            throw new IllegalStateException("No hay una sesión.");
        }
        usuarioActual.agregarSaldo(monto);
        usuarioManager.guardar();
    }

    /**
     * Agrega una cantidad en forma de cadena al saldo del usuario actual.
     * @param monto monto el que se va a depositar.
     */
    public void depositar(String monto) {
        depositar(parsearMonto(monto));
    }

    public void recargar(int monto) {
        if (usuarioActual == null) {
            throw new IllegalStateException("No hay una sesión.");
        }
        if (monto < 0) {
            throw new IllegalArgumentException("El monto a recargar no puede ser negativo.");
        }
        depositar(monto);
    }

    public void recargar(String monto) {
        recargar(parsearMonto(monto));
    }

    /**
     * Verifica si es que existe un usuario.
     * @return true si hay un usuario en sesión, false en caso contrario.
     */
    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    /**
     * @return el nombre del usuario actual.
     */
    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    /**
     * Retorna el usuario actual como objeto.
     * @return el usuario actual.
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Cierra la sesión actual, convierte el usuario actual en nulo.
     */
    public void cerrarSesion() {
        usuarioActual = null;
    }

    /**
     * Registra una jugada en el historial del usuario actual.
     * @param resultado el resultado que guardar.
     */
    public void registrarJugada(Resultado resultado) {
        if (usuarioActual == null) {
            throw new IllegalStateException("No hay sesión.");
        }
        usuarioActual.registrarJugada(resultado);
        usuarioManager.guardar();
    }

    /**
     * Permite convertir el monto expresado como cadena a entero.
     * @param montoTexto el monto en forma de cadena.
     * @return el monto en forma de entero.
     */
    private int parsearMonto(String montoTexto) {
        if (montoTexto == null) {
            throw new IllegalArgumentException("El monto ingresado debe ser un número.");
        } try {
            return Integer.parseInt(montoTexto.trim());
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El monto debe ser un número entero.");
        }
    }

    public List<Resultado> obtenerHistorial() {
        return usuarioActual.getHistorial();
    }
}
