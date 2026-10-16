package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

import java.util.List;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;

    public RuletaController() {
        this(new Ruleta(), new SessionController());
    }

    public RuletaController(Ruleta ruleta) {
        this(ruleta, new SessionController());
    }

    public RuletaController(SessionController session) {
        this(new Ruleta(), session);
    }

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta = ruleta;
        this.session = session;
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public void setSaldo(int saldo) {
        ruleta.setSaldo(saldo);
    }

    public List<Resultado> getHistorial() {
        return ruleta.getHistorial();
    }

    public void depositar(String monto) {
        ruleta.depositar(parsearMonto(monto));
        session.setSaldo(parsearMonto(monto));
    }

    public String realizarApuesta(TipoApuesta tipoApuesta, String monto) {
        Resultado resultado = ruleta.iniciarRonda(tipoApuesta, parsearMonto(monto));
        session.setSaldo(ruleta.getSaldo());
        session.registrarJugada(resultado);
        return resultado.toString();
    }

    private int parsearMonto(String montoTexto) {
        if (montoTexto == null) {
            throw new IllegalArgumentException("El monto ingresado debe ser un número.");
        }
        try {
            return Integer.parseInt(montoTexto.trim());
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El monto debe ser un número entero.");
        }
    }
}
