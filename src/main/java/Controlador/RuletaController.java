package Controlador;

import Modelo.ResultadoApuesta;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

public class RuletaController {
    private final Ruleta ruleta;

    public RuletaController() {
        this(new Ruleta());
    }

    public RuletaController(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public void depositar(int monto) {
        ruleta.depositar(monto);
    }

    public ResultadoApuesta realizarApuesta(TipoApuesta tipoApuesta, int monto) {
        return ruleta.iniciarRonda(tipoApuesta, monto);
    }

    private int parsearMonto(String montoTexto) {
        if(montoTexto == null) {
            throw new IllegalArgumentException("El monto ingresado debe ser un número.")
        }
        try {
            return Integer.parseInt(montoTexto.trim());
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("EL monto debe ser mayor que cero.")
        }
    }
}
