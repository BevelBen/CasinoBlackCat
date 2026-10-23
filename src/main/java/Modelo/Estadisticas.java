package Modelo;

import java.util.List;

public class Estadisticas {

	private final List<Resultado> resultados;
	private final int totalJugadas;
	private final int victorias;
	private final double porcentajeVictorias;
	private final int rachaMaxima;
	private final TipoApuesta tipoMasJugado;

	/**
	 *
	 * @param resultados lista con los resultados a analizar.
	 */
	public Estadisticas(List<Resultado> resultados) {
		this.resultados = resultados;

		totalJugadas = calcularTotalJugadas();
		victorias = calcularVictorias();
		porcentajeVictorias = calcularPorcentajeVictorias();
		rachaMaxima = calcularRachaMaxima();
		tipoMasJugado = calcularTipoMasJugado();
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

	private int calcularTotalJugadas() {
		return resultados.size();
	}

	private int calcularVictorias() {
		int i = 0;
		for (Resultado resultado : resultados) {
			if (resultado.getAcerto()) {
				i++;
			}
		}
		return i;
	}

	private double calcularPorcentajeVictorias() {
		return (totalJugadas == 0) ? 0.0 : 100.0 / totalJugadas;
	}

	private int calcularRachaMaxima() {
		int maximaVista = 0;
		int actualVista = 0;
		for (Resultado resultado : resultados) {
			if (resultado.getAcerto()) {
				actualVista++;
				if (actualVista > maximaVista) {
					maximaVista = actualVista;
				}
			} else {
				actualVista = 0;
			}
		}
		return maximaVista;
	}

	private TipoApuesta calcularTipoMasJugado() {
		TipoApuesta masJugado = null;
		int mayor = 0;
		for (TipoApuesta tipo : TipoApuesta.values()) {
			int cantidad = 0;
			for (Resultado resultado : resultados) {
				if (resultado.getTipoApuesta() == tipo) {
					cantidad++;
				}
			}
			if (cantidad > mayor) {
				mayor = cantidad;
				masJugado = tipo;
			}
		}
		return masJugado;
	}
}