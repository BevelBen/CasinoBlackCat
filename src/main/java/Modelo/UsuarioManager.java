package Modelo;

import java.util.ArrayList;

public class UsuarioManager {
    private final ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private final PersistenciaUsuarios persistenciaUsuarios;

    public UsuarioManager() {
        this.persistenciaUsuarios = null;
    }

    public UsuarioManager(PersistenciaUsuarios persistenciaUsuarios) {
        this.persistenciaUsuarios = persistenciaUsuarios;
        if (persistenciaUsuarios.existeRuta()) {
            listaUsuarios.addAll(persistenciaUsuarios.cargar());
        }
    }

    public void guardar() {
        if (persistenciaUsuarios != null) {
            persistenciaUsuarios.guardar(listaUsuarios);
        }
    }

    /**
     * Verifica si es que un usuario existe en la lista de usuarios.
     * @param username el username del usuario.
     * @return true si existe, false en caso contrario.
     */
    public boolean verificarUsuario(String username) {
        return buscarUsuario(username) != null;
    }

    /**
     * Buscar un usuario por el username.
     * @param username username que se desea buscar.
     * @return el usuario que se encontró.
     */
    public Usuario buscarUsuario(String username) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Permite agregar un nuevo usuario a la lista de usuarios.
     * @param username el username del nuevo usuario.
     * @param clave la clave del nuevo usuario.
     * @param nombre el nombre del nuevo usuario.
     * @return el usuario creado.
     */
    public Usuario agregarUsuario(String username, String clave, String nombre) {
        if (username == null || clave == null || nombre == null) {
            throw new IllegalArgumentException("Datos requeridos.");
        }
        Usuario nuevoUsuario = new Usuario(username, clave, nombre);
        listaUsuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }

    /**
     * Eliminar un usuario de la lista de usuarios.
     * @param username el username del usuario que se desea eliminar.
     * @return el usuario eliminado.
     */
    public Usuario eliminarUsuario(String username) {
        Usuario antiguoUsuario = buscarUsuario(username);
        if (antiguoUsuario == null) {
            throw new IllegalArgumentException("No existe el usuario.");
        }
        listaUsuarios.remove(antiguoUsuario);
        return antiguoUsuario;
    }

    /**
     * Cambia el saldo de un usuario por el username.
     * @param username el username del usuario que se desea modificar.
     * @param saldo el sualdo al cual se desea cambiar.
     */
    public void setSaldo(String username, int saldo) {
        Usuario usuario = buscarUsuario(username);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe el usuario.");
        }
        usuario.setSaldo(saldo);
    }

    /**
     * Cambia el nombre de un usuario por el username.
     * @param username el username del usuario que se desea modificar.
     * @param nombre el nombre al cual se desea cambiar.
     */
    public void setNombre(String username, String nombre) {
        Usuario usuario = buscarUsuario(username);
        if (usuario == null) {
            throw new IllegalArgumentException("No existe el usuario.");
        }
        usuario.setNombre(nombre);
    }

    public boolean validarCredenciales(String username, String password) {
        Usuario usuario = buscarUsuario(username);
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        return usuario.validarCredenciales(password);
    }
}
