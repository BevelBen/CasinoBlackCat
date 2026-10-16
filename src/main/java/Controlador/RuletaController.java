package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

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

    /**
     * Realiza una apuesta desde la ruleta y devuelve el resultado.
     * @param tipoApuesta el tipo de apuesta en enum.
     * @param monto el monto apostado.
     * @return el resultado como un objeto del tipo Resultado.
     */
    public Resultado realizarApuesta(TipoApuesta tipoApuesta, String monto) {
        int montoEntero = parsearMonto(monto);
        if (sessionController.getSaldo() < montoEntero) {
            throw new IllegalArgumentException("No hay suficiente saldo para realizar la apuesta.");
        }
        return ruleta.iniciarRonda(tipoApuesta, montoEntero);
    }

    /**
     * Convierte una cadena de texto a una cantidad entera.
     * @param montoTexto la cantidad en forma de cadena.
     * @return la cantidad en foram de entero.
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
