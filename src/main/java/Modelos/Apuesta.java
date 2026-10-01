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

    @Override
    public String toString() {
        return "El número que salio de la ruleta fue " + numeroRuleta +
                ". El tipo de apuesta fue " + apuesta +
                ". El monto apostado fue de " + monto +
                ". El valor de verdad del acierto fue de " + acerto +
                ".";
    }
}
