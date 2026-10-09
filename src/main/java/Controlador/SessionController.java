package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioManager;

public class SessionController {
    private Usuario usuarioActual;
    private UsuarioManager usuarioManager;

    public SessionController() {
        this(new UsuarioManager());
    }

    public SessionController(UsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }

    public boolean verificarUsuario(String usuario) {
        return usuarioManager.verificarUsuario(usuario);
    }

    public void registrarUsuario(String usuario, String clave, String nombre) {
        usuarioManager.agregarUsuario(usuario, clave, nombre);
    }

    public boolean iniciarSesion(String usuario, String clave) {
        Usuario usuarioSesion = usuarioManager.buscarUsuario(usuario);
        if (usuarioSesion != null) {
            if (usuarioSesion.validarCredenciales(clave)) {
                usuarioActual = usuarioSesion;
                return true;
            }
        }
        return false;
    }

    public void cambiarNombre(String nombre) {
        if (!hayUsuario()) {
            throw new IllegalStateException("No hay una sesión.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }
        usuarioActual.setNombre(nombre);
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}
