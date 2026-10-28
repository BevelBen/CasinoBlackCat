package Controlador;

import Modelo.Estadisticas;

public class EstadisticasController {
    private final Estadisticas estadisticas;

    public EstadisticasController(SessionController sessionController) {
        this.estadisticas = new Estadisticas(sessionController.obtenerHistorial());
    }

    public String obtenerEstadisticas() {
        return "Total Jugadas: " + estadisticas.getTotalJugadas()
                + "\nVictorias: " + estadisticas.getVictorias()
                + "\nPorcentaje de Victorias: " + estadisticas.getPorcentajeVictorias()
                + "\nRacha Maxima de Aciertos: " + estadisticas.getRachaMaxima()
                + "\nTipo de Apuesta Más Jugada: " + ((estadisticas.getTipoMasJugado() == null) ? "Sin datos" : estadisticas.getTipoMasJugado());
    }
}
