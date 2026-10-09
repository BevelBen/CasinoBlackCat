package Controlador;

import Modelo.Ruleta;
import Modelo.TipoApuesta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RuletaControllerTest {

    @Test
    @DisplayName("El controlador sin parámetros parte con saldo cero")
    void constructorSinParametros_saldoCero() {
        assertEquals(0, new RuletaController().getSaldo());
    }

    @Test
    @DisplayName("getSaldo refleja el saldo de la ruleta entregada")
    void getSaldo_delegaEnLaRuleta() {
        assertEquals(75, new RuletaController(new Ruleta(75)).getSaldo());
    }

    @Test
    @DisplayName("depositar convierte el texto a número y lo suma al saldo")
    void depositar_textoValido() {
        RuletaController c = new RuletaController();
        c.depositar("100");
        c.depositar("  50  ");
        assertEquals(150, c.getSaldo());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "", "12.5", "1 000", "diez"})
    @DisplayName("depositar rechaza textos que no son números enteros")
    void depositar_textoNoNumerico(String texto) {
        RuletaController c = new RuletaController();
        assertThrows(IllegalArgumentException.class, () -> c.depositar(texto));
        assertEquals(0, c.getSaldo());
    }

    @Test
    @DisplayName("depositar rechaza null")
    void depositar_null() {
        RuletaController c = new RuletaController();
        assertThrows(IllegalArgumentException.class, () -> c.depositar(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-5"})
    @DisplayName("depositar rechaza montos cero o negativos")
    void depositar_montoNoPositivo(String texto) {
        RuletaController c = new RuletaController();
        assertThrows(IllegalArgumentException.class, () -> c.depositar(texto));
        assertEquals(0, c.getSaldo());
    }

    @Test
    @DisplayName("realizarApuesta con monto no numérico lanza IllegalArgumentException")
    void realizarApuesta_montoNoNumerico() {
        RuletaController c = new RuletaController(new Ruleta(100));
        assertThrows(IllegalArgumentException.class, () -> c.realizarApuesta(TipoApuesta.ROJO, "mucho"));
        assertEquals(100, c.getSaldo());
    }

    @Test
    @DisplayName("realizarApuesta sin saldo suficiente lanza IllegalStateException")
    void realizarApuesta_saldoInsuficiente() {
        RuletaController c = new RuletaController(new Ruleta(10));
        assertThrows(IllegalStateException.class, () -> c.realizarApuesta(TipoApuesta.ROJO, "11"));
        assertEquals(10, c.getSaldo());
    }
}