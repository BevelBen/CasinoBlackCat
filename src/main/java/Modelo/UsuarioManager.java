package Modelo;

import java.util.ArrayList;

public class UsuarioManager {
    private final ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

    public UsuarioManager() {
        listaUsuarios.add(new Usuario("benjamin", "123", "Benjamin"));
    }

    public boolean verificarUsuario(String username) {
        return buscarUsuario(username) != null;
    }

    public boolean verificarClave(String username, String clave) {
        Usuario usuarioVerificado = buscarUsuario(username);
        return usuarioVerificado.validarCredenciales(clave);
    }

    public Usuario buscarUsuario(String username) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public Usuario agregarUsuario(String usuario, String clave, String nombre) {
        if (usuario == null || clave == null || nombre == null) {
            throw new IllegalArgumentException("Datos requeridos.");
        }
        Usuario nuevoUsuario = new Usuario(usuario, clave, nombre);
        listaUsuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }

    public Usuario eliminarUsuario(String usuario) {
        Usuario antiguoUsuario = buscarUsuario(usuario);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe el usuario.");
        }
        listaUsuarios.remove(antiguoUsuario);
        return antiguoUsuario;
    }
}
