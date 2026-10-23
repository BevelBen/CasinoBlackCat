package Modelo;

public class Estadisticas {

	private List<Resultado> resultados;
	private int totalJugadas;
	private int victorias;
	private double porcentajeVictorias;
	private int rachaMaxima;
	private TipoApuesta tipoMasJugado;

	/**
	 * 
	 * @param resultados
	 */
	public Estadisticas(List<Resultado> resultados) {
		// TODO - implement Estadisticas.Estadisticas
		throw new UnsupportedOperationException();
	}

	public int getTotalJugadas() {
		return this.totalJugadas;
	}

	public int getVictorias() {
		return this.victorias;
	}

	public double getPorcentajeVictorias() {
		return this.porcentajeVictorias;
	}

	public int getRachaMaxima() {
		return this.rachaMaxima;
	}

	public TipoApuesta getTipoMasJugado() {
		return this.tipoMasJugado;
	}

}