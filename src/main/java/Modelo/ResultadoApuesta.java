package Modelo;

public class ResultadoApuesta {
    private int numeroRuleta;
    private TipoApuesta tipoApuesta;
    private int monto;
    private boolean acerto;

    public ResultadoApuesta(int numeroRuleta, TipoApuesta tipoApuesta, int monto, boolean acerto) {
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

    @Override
    public String toString() {
        return "El número que salio de la ruleta fue " + numeroRuleta +
                ".\n El tipo de apuesta fue " + tipoApuesta +
                ".\n El monto apostado fue de " + monto +
                ".\n" + (acerto ? "¡Ganaste! Se ganó " + monto + "." : "Perdiste... Se perdió " + monto + ".");
    }
}
