package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private int saldo;
    private List<Resultado> historial;

    public Usuario() {
        this("invitado", "invitado", "invitado");
    }

    public Usuario(String username, String password, String nombre) {
        this(username, password, nombre, 0);
    }

    public Usuario(String username, String password, String nombre, int saldo) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.saldo = saldo;

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

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo.");
        }
        this.saldo = saldo;
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