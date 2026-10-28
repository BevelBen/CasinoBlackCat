package Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {

	private final List<Resultado> resultados;
	private final int totalJugadas;
	private final int victorias;
	private final double porcentajeVictorias;
	private final int rachaMaxima;
	private final String tipoMasJugado;

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

	public String getTipoMasJugado() {
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
		return (totalJugadas == 0) ? 0.0 : (double) victorias / totalJugadas * 100.0;
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

	private String calcularTipoMasJugado() { // ENCUENTRA EL TIPO MAS JUGADO COMO CADENA UTILIZANDO UN MAP
		Map<String, Integer> conteo = new HashMap<>();
		String masJugado = null;
		int mayor = 0;
		for (Resultado resultado : resultados) {
			String etiqueta = resultado.getEtiquetaApuesta();
			int cantidad = conteo.merge(etiqueta, 1, Integer::sum);
			if (cantidad > mayor) {
				mayor = cantidad;
				masJugado = etiqueta;
			}
		}
		return masJugado;
	}
}