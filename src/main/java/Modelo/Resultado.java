package Modelo;

public class Resultado {
    private final int numeroRuleta;
    private final String etiquetaApuesta;
    private final int monto;
    private final boolean acerto;

    public Resultado(int numeroRuleta, String etiquetaApuesta, int monto, boolean acerto) {
        this.numeroRuleta = numeroRuleta;
        this.etiquetaApuesta = etiquetaApuesta;
        this.monto = monto;
        this.acerto = acerto;
    }

    public int getNumeroRuleta() {
        return numeroRuleta;
    }

    public int getMonto() {
        return monto;
    }

    public String getEtiquetaApuesta() {
        return etiquetaApuesta;
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
                ".\nEl tipo de apuesta fue " + etiquetaApuesta +
                ".\nEl monto apostado fue de " + monto +
                ".\n" + (acerto ? "Ganaste, se ganó " + monto + "." : "Perdiste, se perdió " + monto + ".");
    }
}
