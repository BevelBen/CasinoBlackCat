package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private final String username;
    private final String password;
    private String nombre;
    private int saldo;
    private final List<Resultado> historial;

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

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public int getSaldo() {
        return saldo;
    }

    protected String getPassword() {
        return password;
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

    /**
     * @return el historial como una lista no modificable.
     */
    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    /**
     * Verifica las credenciales del usuario.
     * @param password la clave que se desea verificar.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    public boolean validarCredenciales(String password) {
        if (password == null) {
            return false;
        }
        return (password.equals(this.password));
    }

    /**
     * Registra un resultado en el historial del usuario.
     * @param resultado el resultado que se desea guardar.
     */
    public void registrarJugada(Resultado resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("El resultado no puede ser nulo.");
        }
        historial.add(resultado);
    }

    /**
     * Agrega una cantidad de saldo al usuario.
     * @param saldo el saldo que se desea agregar.
     */
    public void agregarSaldo(int saldo) {
        this.saldo += saldo;
    }
}