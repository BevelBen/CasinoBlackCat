import java.util.Random;
import java.util.Scanner;
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

    /**
     * Inicia el programa y invoca el menú.
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Controla el flujo principal del programa mostrando
     * un menú en consola.
     */
    public static void menu() {
        mostrarMenu();

        Scanner scanner = new Scanner(System.in);
        int opcion = leerEntero(scanner);
        if (validarOpcion(opcion)) {
            ejecutarOpcion(opcion, scanner);
        }
    }

    /**
     * Muestra en consola las opciones disponibles del menú.
     */
    public static void mostrarMenu() {
        IO.println("1. Ejecutar Ronda");
        IO.println("2. Ver Historial");
        IO.println("3. Salir");
    }

    /**
     * Lee un entero ingresado por el usuario desde teclado.
     *
     * @param in Scanner para entrada por consola.
     * @return número entero ingresado.
     */
    public static int leerEntero(Scanner in) {
        return in.nextInt();
    }

    /**
     * Valida la opción ingresada.
     *
     * @param opcion entero que se desea validar.
     * @return true si es valido, false en caso contrario..
     */
    public static boolean validarOpcion(int opcion) {
        return (opcion >= 1 && opcion <= 3);
    }

    /**
     * Ejecuta la acción correspondiente a la opción del menú.
     *
     * @param opcion opción elegida por el usuario.
     * @param in Scanner para entrada por consola.*/
    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                iniciarRonda(in);
            case 2:
                mostrarEstadisticas();
            case 3:
                // SALIR DEL PROGRAMA
        }
    }

    /**
     * Inicia una ronda de la ruleta: leer apuesta, girar,
     * evaluar y mostrar resultado.
     *
     * @param in Scanner para entrada por consola.
     */
    public static void iniciarRonda(Scanner in) {
        char apuesta = leerTipoApuesta(in);
        int monto = leerEntero(in);
        int numero = girarRuleta();
        boolean acerto = evaluarResultado(numero, apuesta);

        // REGISTRAR RESULTADO
    }

    /**
     * Permite al usuario seleccionar el tipo de apuesta
     * (R/N/P/I).
     *
     * @param in Scanner para entrada por consola.
     * @return el tipo de apuesta elegido.
     */
    public static char leerTipoApuesta(Scanner in) {
        while (true) {
            char tipo = leerCaracter(in);
            if (validarTipoApuesta(tipo)) {
                return tipo;
            }
        }
    }

    public static char leerCaracter(Scanner in) {
        return in.next().charAt(0);
    }

    /**
     * Valida que el caracter ingresado corresponde con
     * una apuesta aceptada.
     *
     * @param tipo tipo en forma de carácter.
     * @return true si es valido, false en caso contrario.
     */
    public static boolean validarTipoApuesta(char tipo) {
        return switch (tipo) {
            case OPCION_ROJO, OPCION_NEGRO, OPCION_PAR, OPCION_IMPAR -> true;
            default -> false;
        };
    }

    /**
     * Simula el giro de la ruleta generando un número
     * aleatorio de 0 a 36.
     *
     * @return número de la ruleta.
     */
    public static int girarRuleta() {
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
    public static boolean evaluarResultado(int numero, char tipo) {
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
    public static boolean buscarEnteroArreglo(int[] v, int n) {
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
    public static boolean esRojo(int n) {
        return buscarEnteroArreglo(numerosRojos, n);
    }

    /**
     * Determina si un número es par.
     *e
     * @param n número cuya paridad se verifica.
     * @return true si es par, false en caso contrario.
     */
    public static boolean esPar(int n) {
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
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (verificarEspacioHistorial()) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
        }
        else {
            IO.println("El historial esta lleno.");
        }
    }

    public static boolean verificarEspacioHistorial() {
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
    public static void mostrarResultado(int numero, char tipo, int monto, boolean
            acierto) {
// TODO: Mostrar los datos y el resultado de la ronda.
    }

    /**
     * Muestra estadísticas generales de todas las
     * rondas jugadas.
     */
    public static void mostrarEstadisticas() {
// TODO: Calcular y mostrar las estadísticas acumuladas.
    }
}