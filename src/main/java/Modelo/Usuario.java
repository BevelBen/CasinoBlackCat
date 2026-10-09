package Modelo;

public class Usuario {
    private String username;
    private String password;
    private String nombre;

    public Usuario() {
        this("invitado", "invitado", "invitado");
    }

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    // Verifica si las credenciales ingresadas pertenecen al usuario
    public boolean validarCredenciales(String p) {
        return (password.equals(p));
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}