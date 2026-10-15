package Controlador;

import Modelo.Resultado;
import Modelo.Ruleta;
import Modelo.TipoApuesta;
import Modelo.Usuario;

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

    public List<Resultado> getHistorial() {
        return ruleta.getHistorial();
    }

    public void depositar(String monto) {
        ruleta.depositar(parsearMonto(monto));
    }

    public String realizarApuesta(TipoApuesta tipoApuesta, String monto) {
        Usuario usuario = obtenerUsuario();
        Resultado resultado = ruleta.iniciarRonda(tipoApuesta, parsearMonto(monto));
        usuario.registrarJugada(resultado);
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

    private Usuario obtenerUsuario() {
        Usuario usuario = session.getUsuarioActual();
        if (usuario == null) {
            throw new IllegalStateException("No hay usuario.");
        }
        return usuario;
    }
}
