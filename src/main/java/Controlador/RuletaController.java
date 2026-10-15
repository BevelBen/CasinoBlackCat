package Controlador;

import Modelo.ResultadoApuesta;
import Modelo.Ruleta;
import Modelo.TipoApuesta;

import java.util.List;

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

    public List<ResultadoApuesta> getHistorial() {
        return ruleta.getHistorial();
    }

    public void depositar(String monto) {
        ruleta.depositar(parsearMonto(monto));
    }

    public ResultadoApuesta realizarApuesta(TipoApuesta tipoApuesta, String monto) {
        return ruleta.iniciarRonda(tipoApuesta, parsearMonto(monto));
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
