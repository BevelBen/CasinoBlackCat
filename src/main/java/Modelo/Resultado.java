package Modelo;

public class Resultado {
    private final int numeroRuleta;
    private final TipoApuesta tipoApuesta;
    private final int monto;
    private final boolean acerto;

    public Resultado(int numeroRuleta, TipoApuesta tipoApuesta, int monto, boolean acerto) {
        this.numeroRuleta = numeroRuleta;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
        this.acerto = acerto;
    }

    public int getNumeroRuleta() {
        return numeroRuleta;
    }

    public int getMonto() {
        return monto;
    }

    public TipoApuesta getTipoApuesta() {
        return tipoApuesta;
    }

    public boolean getAcerto() {
        return acerto;
    }

    public int getMontoGanado() {
        return acerto ? monto : -monto;
    }

    @Override
    public String toString() {
        return "El número que salio de la ruleta fue " + numeroRuleta +
                ".\n El tipo de apuesta fue " + tipoApuesta +
                ".\n El monto apostado fue de " + monto +
                ".\n" + (acerto ? "Ganaste, se ganó " + monto + "." : "Perdiste, se perdió " + monto + ".");
    }
}
