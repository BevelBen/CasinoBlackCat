package Modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoApuestaTest {

    @Test
    @DisplayName("Los getters devuelven los valores entregados al constructor")
    void getters_devuelvenLosDatos() {
        ResultadoApuesta r = new ResultadoApuesta(17, TipoApuesta.NEGRO, 40, true);
        assertEquals(17, r.getNumeroRuleta());
        assertEquals(TipoApuesta.NEGRO, r.getTipoApuesta());
        assertEquals(40, r.getMonto());
        assertTrue(r.getAcerto());
    }

    @Test
    @DisplayName("toString de una apuesta ganada indica victoria y el monto ganado")
    void toString_apuestaGanada() {
        String texto = new ResultadoApuesta(1, TipoApuesta.ROJO, 50, true).toString();
        assertTrue(texto.contains("Ganaste"));
        assertTrue(texto.contains("Se ganó 50"));
        assertFalse(texto.contains("Perdiste"));
    }

    @Test
    @DisplayName("toString de una apuesta perdida indica derrota y el monto perdido")
    void toString_apuestaPerdida() {
        String texto = new ResultadoApuesta(2, TipoApuesta.ROJO, 50, false).toString();
        assertTrue(texto.contains("Perdiste"));
        assertTrue(texto.contains("Se perdió 50"));
        assertFalse(texto.contains("Ganaste"));
    }

    @Test
    @DisplayName("toString incluye el número que salió y el tipo de apuesta")
    void toString_incluyeNumeroYTipo() {
        String texto = new ResultadoApuesta(23, TipoApuesta.IMPAR, 5, true).toString();
        assertTrue(texto.contains("23"));
        assertTrue(texto.contains("IMPAR"));
    }
}