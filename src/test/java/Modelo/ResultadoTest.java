package Modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoTest {

    @Test
    @DisplayName("Los getters devuelven los valores entregados al constructor")
    void getters_devuelvenLosDatos() {
        Resultado r = new Resultado(17, TipoApuesta.NEGRO, 40, true);
        assertEquals(17, r.getNumeroRuleta());
        assertEquals(TipoApuesta.NEGRO, r.getTipoApuesta());
        assertEquals(40, r.getMonto());
        assertTrue(r.getAcerto());
    }

    @Test
    @DisplayName("toString incluye el número que salió y el tipo de apuesta")
    void toString_incluyeNumeroYTipo() {
        String texto = new Resultado(23, TipoApuesta.IMPAR, 5, true).toString();
        assertTrue(texto.contains("23"));
        assertTrue(texto.contains("IMPAR"));
    }
}