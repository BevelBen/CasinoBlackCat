package Modelo;

public abstract class ApuestaBase {
    private final int montoApostado;
    private final String etiquetaApuesta;

    public ApuestaBase(int montoApostado, String etiquetaApuesta) {
        this.montoApostado = montoApostado;
        this.etiquetaApuesta = etiquetaApuesta;
    }

    public abstract boolean acierta(int numero, String color);

    public int getMontoApostado() {
        return montoApostado;
    }

    public String getEtiquetaApuesta() {
        return etiquetaApuesta;
    }
}
