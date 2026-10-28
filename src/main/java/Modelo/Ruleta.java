package Modelo;

import java.util.*;

public class Ruleta {
    private final Random rng = new Random();
    private final int[] NUMEROS_ROJOS = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };
    private final List<Resultado> resultados = new ArrayList<>();

    public List<Resultado> getResultados() {
        return Collections.unmodifiableList(resultados);
    }

    /**
     * Válida que el número ingresado sea mayor que cero.
     *
     * @param monto monto que se desea validar.
     * @return true si es válido, falso en caso contrario.
     */
    public boolean validarMonto(int monto) {
        return (monto > 0);
    }

    /**
     * Evalúa una ronda; válida el monto, gira la ruleta, añade el resultado al historial
     * y retorna el resultado como un objeto de Resultado.
     * @param tipoApuesta tipo de apuesta.
     */
    public Resultado iniciarRonda(ApuestaBase apuestaBase) {
        if (apuestaBase == null) {
            throw new IllegalArgumentException("Apuesta no valida.");
        }
        if (!validarMonto(apuestaBase.getMontoApostado())) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }

        int numero = girarRuleta();
        String color = evaluarColor(numero);
        boolean acerto = apuestaBase.acierta(numero, color);

        Resultado resultado = new Resultado(numero, apuestaBase.getEtiquetaApuesta(), apuestaBase.getMontoApostado(), acerto);
        resultados.add(resultado);
        return resultado;
    }

    /**
     * Simula el giro de la ruleta generando un número
     * aleatorio de 0 a 36.
     *
     * @return número de la ruleta.
     */
    private int girarRuleta() {
        return rng.nextInt(37);
    }

    /**
     * Entrega el color del número n.
     * @param n el numero obtenido.
     * @return el color como cadena.
     */
    private String evaluarColor(int n) {
        return esRojo(n) ? "ROJO" : "NEGRO";
    }

    /**
     * Determina si un número corresponde a color rojo.
     * @param n número de la ruleta.
     *
     * @return true si es rojo, false en caso contrario.
     */
    private boolean esRojo(int n) {
        int i = 0;
        while (i < NUMEROS_ROJOS.length) {
            if (n == NUMEROS_ROJOS[i]) {
                return true;
            }
            i++;
        }
        return false;
    }
}