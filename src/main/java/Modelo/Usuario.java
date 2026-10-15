package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private List<Resultado> historial;

    public Usuario() {
        this("invitado", "invitado", "invitado");
    }

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;

        this.historial = new ArrayList<>();
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

    public void registrarJugada(Resultado resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("El resultado no puede ser nulo.");
        }
        historial.add(resultado);
    }

    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
}