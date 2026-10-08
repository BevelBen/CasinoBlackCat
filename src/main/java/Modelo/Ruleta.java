package Modelo;

import java.util.Random;

public class Ruleta {
    private static final int MAX_HISTORIAL = 100;
    private int[] historialNumeros = new int[MAX_HISTORIAL];
    private int[] historialApuestas = new int[MAX_HISTORIAL];
    private boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private int historialSize = 0;
    private int saldo;
    private Random rng = new Random();
    private static final int[] NUMEROS_ROJOS = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    public Ruleta() {
        this(0);
    }

    public Ruleta(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    /**
     * Valida que el número ingresado sea mayor que cero.
     *
     * @param monto monto que se desea validar.
     * @return true si es valido, falso en caso contrario.
     */
    public boolean validarMonto(int monto) {
        return (monto > 0);
    }

    public void depositar(int monto) {
        if (!validarMonto(monto)) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que cero.");
        }
        saldo += monto;
    }

    /**
     * Inicia una ronda de la ruleta: leer apuesta, girar,
     * evaluar y mostrar resultado.
     *
     * @param tipoApuesta tipo de apuesta.
     */
    public ResultadoApuesta iniciarRonda(TipoApuesta tipoApuesta, int monto) {
        if (!validarMonto(monto)) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        if (monto > saldo) {
            throw new IllegalStateException("No hay suficiente saldo para apostar.");
        }
        int numero = girarRuleta();
        boolean acerto = evaluarResultado(numero, tipoApuesta);

        saldo += acerto ? monto : -monto;
        registrarResultado(numero, monto, acerto);
        return new ResultadoApuesta(numero, tipoApuesta, monto, acerto);
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
     * @param tipoApuesta tipo de apuesta elegida.
     * @return true si acertó, false si perdió.
     */
    private boolean evaluarResultado(int numero, TipoApuesta tipoApuesta) {
        return switch (tipoApuesta) {
            case ROJO -> esRojo(numero);
            case NEGRO -> !esRojo(numero);
            case PAR -> esPar(numero);
            case IMPAR -> !esPar(numero);
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
        return buscarEnteroArreglo(NUMEROS_ROJOS, n);
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
}