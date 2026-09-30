package Modelos;

public class Apuesta {
    private int numeroRuleta;
    private char apuesta;
    private int monto;
    private boolean acerto;

    public Apuesta(int numeroRuleta, char apuesta, int monto, boolean acerto) {
        this.numeroRuleta = numeroRuleta;
        this.apuesta = apuesta;
        this.monto = monto;
        this.acerto = acerto;
    }

    public int getNumeroRuleta() {
        return numeroRuleta;
    }

    public char getApuesta() {
        return apuesta;
    }

    public int getMonto() {
        return monto;
    }

    public boolean getAcerto() {
        return acerto;
    }
}
