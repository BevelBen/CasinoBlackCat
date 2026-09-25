package Servicios;

import java.util.Random;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };
    public static final char OPCION_ROJO = 'R';
    public static final char OPCION_NEGRO = 'N';
    public static final char OPCION_PAR = 'P';
    public static final char OPCION_IMPAR = 'I';
    public static int montoApostado = 0;
    public static int montoGanado = 0;

    public String ultimoResultado = "";
    public boolean ultimoAcierto = false;
    public String ultimoAviso = "";
    public String ultimasEstadisticas = "";

    /**
     * Valida que el número ingresado sea mayor que cero.
     *
     * @param monto monto que se desea validar.
     * @return true si es valido, falso en caso contrario.
     */
    public boolean validarMonto(int monto) {
        return (monto > 0);
    }

    /**
     * Inicia una ronda de la ruleta: leer apuesta, girar,
     * evaluar y mostrar resultado.
     *
     * @param apuesta Caracter del tipo de apuesta.
     */
    public String iniciarRonda(char apuesta, int monto) {
        int numero = girarRuleta();
        boolean acerto = evaluarResultado(numero, apuesta);

        registrarResultado(numero, apuesta, acerto);
        return prepararResultado(numero, apuesta, monto, acerto);
    }

    /**
     * Permite al usuario seleccionar el tipo de apuesta
     * (R/N/P/I).
     *
     * @param monto monto que se aposto.
     * @param acerto si es que acerto o no.
     */
    private void agregarMontos(int monto, boolean acerto) {
        montoApostado += monto;
        montoGanado += acerto ? monto : monto * (-1);
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
     * Evalúa si la apuesta realizada por el jugador
     * fue acertada.
     *
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta elegida.
     * @return true si acertó, false si perdió.
     */
    private boolean evaluarResultado(int numero, char tipo) {
        return switch (tipo) {
            case OPCION_ROJO -> esRojo(numero);
            case OPCION_NEGRO -> !esRojo(numero);
            case OPCION_PAR -> esPar(numero);
            case OPCION_IMPAR -> !esPar(numero);
            default -> false;
        };
    }

    /**
     * Determina si un número entero pertenece a un arreglo.
     *
     * @param n número que se desea buscar.
     * @return true si se encuentar, falso en caso contrario.
     */
    private boolean buscarEnteroArreglo(int[] v, int n) {
        int i = 0;
        while (i < v.length) {
            if (n == v[i]) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Determina si un número corresponde a color rojo.
     ** @param n número de la ruleta.
     * @return true si es rojo, false en caso contrario.
     */
    private boolean esRojo(int n) {
        return buscarEnteroArreglo(numerosRojos, n);
    }

    /**
     * Determina si un número es par.
     *e
     * @param n número cuya paridad se verifica.
     * @return true si es par, false en caso contrario.
     */
    private boolean esPar(int n) {
        return n % 2 == 0;
    }

    /**
     * Registra los resultados de la ronda en los arreglos
     * de historial.
     *
     * @param numero número obtenido en la ruleta.
     * @param apuesta monto apostado.
     * @param acierto si el jugador acertó o no.
     */
    private void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (verificarEspacioHistorial()) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
            ultimoAviso = "";
        }
        else {
            ultimoAviso = "El historial esta lleno.";
        }
    }

    /**
     * Permite verificar si es que el historial esta lleno.
     *
     * @return true si hay espacio, falso en caso contrario.
     */
    private boolean verificarEspacioHistorial() {
        return historialSize < MAX_HISTORIAL;
    }

    /**
     * Muestra en consola el resultado de la ronda.
     *
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta realizada.
     * @param monto monto apostado.
     * @param acierto si el jugador ganó o perdió.
     */
    private String prepararResultado(int numero, char tipo, int monto, boolean acierto) {
        return "Número obtenido en la ruleta: " + numero + "\n"
                + "Tipo de apuesta realizada: " + tipo + "\n"
                + "Monto apostado: " + monto + "\n"
                + "Ganaste la apuesta: " + acierto;
    }

    private int cantidadDeEntradasVerdaderas(boolean[] v) {
        int count = 0;
        for (int i = 1; i < v.length; i++) {
            if (v[i]) {
                count++;
            }
        }
        return count;
    }
}