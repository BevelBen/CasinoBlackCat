package Modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {
    @Test
    @DisplayName("validarMonto acepta solo montos mayores que cero")
    void validarMonto_soloPositivos() {
        Ruleta r = new Ruleta();
        assertTrue(r.validarMonto(1));
        assertFalse(r.validarMonto(0));
        assertFalse(r.validarMonto(-10));
    }

    @Test
    @DisplayName("Con la ruleta real (aleatoria) el número siempre está entre 0 y 36")
    void giroReal_numeroEnRango() {
        Ruleta r = new Ruleta();
        for (int i = 0; i < 100; i++) {
            int n = r.iniciarRonda(TipoApuesta.ROJO, 1).getNumeroRuleta();
            assertTrue(n >= 0 && n <= 36, "Número fuera de rango: " + n);
        }
    }
}