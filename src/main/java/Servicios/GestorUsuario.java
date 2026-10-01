package Servicios;

import Modelos.Usuario;

import java.util.ArrayList;

public class GestorUsuario {
    private final ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

    public GestorUsuario() {
        listaUsuarios.add(new Usuario("benjamin", "123", "Benjamin"));
    }

    public boolean verificarUsuario(String username) {
        return buscarUsuario(username) != null;
    }

    /**
     * Valida las credenciales ingresadas utilizando la lista de usuarios.
     *
     * @param username nombre de usuario ingresado
     * @param password contraseña ingresada
     * @return el nombre del usuario si las credenciales son válidas o una cadena vacía
     * si no existe una coincidencia
     */
    public boolean verificarClave(String username, String password) {
        return buscarUsuario(username).validarCredenciales(password);
    }

    private Usuario buscarUsuario(String u) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equals(u)) {
                return usuario;
            }
        }
        return null;
    }
}
