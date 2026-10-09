package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {
    private Ruleta ruleta;

    @BeforeEach
    void setUp() {
        ruleta = new Ruleta(100);
    }

    @Test
    @DisplayName("El constructor sin parámetros inicia con saldo cero")
    void constructorSinParametros_saldoCero() {
        assertEquals(0, new Ruleta().getSaldo());
    }

    @Test
    @DisplayName("El constructor con parámetros inicia con el saldo indicado")
    void constructorConSaldo_asignaSaldo() {
        assertEquals(250, new Ruleta(250).getSaldo());
    }

    @Test
    @DisplayName("validarMonto acepta solo montos mayores que cero")
    void validarMonto_soloPositivos() {
        assertTrue(ruleta.validarMonto(1));
        assertFalse(ruleta.validarMonto(0));
        assertFalse(ruleta.validarMonto(-10));
    }

    @Test
    @DisplayName("depositar suma el monto al saldo")
    void depositar_montoValido_aumentaSaldo() {
        ruleta.depositar(50);
        assertEquals(150, ruleta.getSaldo());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -500})
    @DisplayName("depositar rechaza montos cero o negativos y no cambia el saldo")
    void depositar_montoInvalido_lanzaExcepcion(int monto) {
        assertThrows(IllegalArgumentException.class, () -> ruleta.depositar(monto));
        assertEquals(100, ruleta.getSaldo());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -20})
    @DisplayName("iniciarRonda rechaza montos no positivos")
    void iniciarRonda_montoInvalido_lanzaExcepcion(int monto) {
        assertThrows(IllegalArgumentException.class,
                () -> ruleta.iniciarRonda(TipoApuesta.ROJO, monto));
        assertEquals(100, ruleta.getSaldo());
    }

    @Test
    @DisplayName("iniciarRonda rechaza apostar más que el saldo y no lo modifica")
    void iniciarRonda_saldoInsuficiente_lanzaExcepcion() {
        assertThrows(IllegalStateException.class,
                () -> ruleta.iniciarRonda(TipoApuesta.ROJO, 101));
        assertEquals(100, ruleta.getSaldo());
    }

    @Test
    @DisplayName("Con la ruleta real (aleatoria) el número siempre está entre 0 y 36")
    void giroReal_numeroEnRango() {
        Ruleta r = new Ruleta(1_000_000);
        for (int i = 0; i < 200; i++) {
            int n = r.iniciarRonda(TipoApuesta.ROJO, 1).getNumeroRuleta();
            assertTrue(n >= 0 && n <= 36, "Número fuera de rango: " + n);
        }
    }
}