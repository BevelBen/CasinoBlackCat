package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

import java.util.ArrayList;
import java.util.List;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController sessionController;

    public RuletaController(SessionController sessionController) {
        this(new Ruleta(), sessionController);
    }

    public RuletaController(Ruleta ruleta, SessionController sessionController) {
        this.ruleta = ruleta;
        this.sessionController = sessionController;
    }

    public int getSaldo() {
        return sessionController.getSaldo();
    }

    /**
     * Permite obtener una lista con todos los tipos de apuestas como cadenas.
     * @return la lista con cada tipo de apuesta.
     */
    public List<String> obetenerTiposApuesta() {
        List<String> tipos = new ArrayList<>();
        for (TipoApuesta tipo : TipoApuesta.values()) {
            tipos.add(tipo.name());
        }
        return tipos;
    }

    /**
     * Realiza una apuesta desde la ruleta y devuelve el resultado como cadena.
     * @param tipo el tipo de apuesta en forma de cadena.
     * @param monto el monto apostado en forma de cadena.
     * @return el resultado como una cadena.
     */
    public String realizarApuesta(String tipo, String monto) {
        TipoApuesta tipoApuesta = parsearTipoApuesta(tipo);
        int montoEntero = parsearMonto(monto);

        if (getSaldo() < montoEntero) {
            throw new IllegalArgumentException("No hay suficiente saldo para realizar la apuesta.");
        }

        Resultado resultado = ruleta.iniciarRonda(tipoApuesta, montoEntero);

        sessionController.depositar(resultado.getMontoGanado());
        sessionController.registrarJugada(resultado);

        return resultado.toString();
    }

    /**
     * Convierte una cadena de texto a una cantidad entera.
     * @param montoTexto la cantidad en forma de cadena.
     * @return la cantidad en forma de entero.
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

    /**
     * Convierte una cadena de texto a un tipo de apuesta.
     * @param texto el tipo de apuesta en forma de cadena.
     * @return el tipo de apuesta.
     */
    private TipoApuesta parsearTipoApuesta(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("Debe elegir un tipo de apuesta.");
        }
        try {
            return TipoApuesta.valueOf(texto.trim().toUpperCase()); // Las apuestas se encuentran en mayúsculas.
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El tipo de apuesta no es válido.");
        }
    }
}
