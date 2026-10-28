package Controlador;

import Modelo.*;

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
     * Realiza una apuesta desde la ruleta y devuelve el resultado como cadena.
     * @param tipo el tipo de apuesta en forma de cadena.
     * @param monto el monto apostado en forma de cadena.
     * @return el resultado como una cadena.
     */
    public String realizarApuesta(String tipo, String monto) {
        int montoEntero = parsearMonto(monto);

        if (getSaldo() < montoEntero) {
            throw new IllegalArgumentException("No hay suficiente saldo para realizar la apuesta.");
        }

        ApuestaBase apuestaBase = switch (tipo) {
            case "Impar" -> new ApuestaImpar(montoEntero);
            case "Negro" -> new ApuestaNegro(montoEntero);
            case "Par" -> new ApuestaPar(montoEntero);
            case "Rojo" -> new ApuestaRojo(montoEntero);
            default -> null;
        };

        Resultado resultado = ruleta.iniciarRonda(apuestaBase);

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
}
