package Modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoApuestaTest {

    @Test
    @DisplayName("El enum contiene exactamente ROJO, NEGRO, PAR e IMPAR, en ese orden")
    void values_contieneLosCuatroTipos() {
        assertArrayEquals(
                new TipoApuesta[]{TipoApuesta.ROJO, TipoApuesta.NEGRO, TipoApuesta.PAR, TipoApuesta.IMPAR},
                TipoApuesta.values());
    }

    @Test
    @DisplayName("valueOf convierte un nombre válido en su constante")
    void valueOf_nombreValido() {
        assertEquals(TipoApuesta.PAR, TipoApuesta.valueOf("PAR"));
    }

    @Test
    @DisplayName("valueOf lanza excepción con un nombre que no existe")
    void valueOf_nombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> TipoApuesta.valueOf("VERDE"));
    }

    @Test
    @DisplayName("name() y valueOf() son operaciones inversas")
    void name_valueOf_roundTrip() {
        for (TipoApuesta t : TipoApuesta.values()) {
            assertEquals(t, TipoApuesta.valueOf(t.name()));
        }
    }
}