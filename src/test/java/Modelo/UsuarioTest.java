package Modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    @DisplayName("El constructor con parámetros guarda username y nombre")
    void constructorConParametros_asignaDatos() {
        Usuario u = new Usuario("ana", "clave", "Ana");
        assertEquals("ana", u.getUsername());
        assertEquals("Ana", u.getNombre());
    }
    @Test
    @DisplayName("validarCredenciales acepta la clave correcta")
    void validarCredenciales_claveCorrecta() {
        assertTrue(new Usuario("ana", "clave", "Ana").validarCredenciales("clave"));
    }

    @Test
    @DisplayName("validarCredenciales rechaza claves incorrectas, vacías o nulas")
    void validarCredenciales_claveIncorrecta() {
        Usuario u = new Usuario("ana", "clave", "Ana");
        assertFalse(u.validarCredenciales("otra"));
        assertFalse(u.validarCredenciales(""));
        assertFalse(u.validarCredenciales(null));
    }

    @Test
    @DisplayName("validarCredenciales distingue mayúsculas de minúsculas")
    void validarCredenciales_distingueMayusculas() {
        assertFalse(new Usuario("ana", "Clave", "Ana").validarCredenciales("clave"));
    }

    @Test
    @DisplayName("setNombre actualiza el nombre pero no el username")
    void setNombre_actualizaSoloElNombre() {
        Usuario u = new Usuario("ana", "clave", "Ana");
        u.setNombre("Ana María");
        assertEquals("Ana María", u.getNombre());
        assertEquals("ana", u.getUsername());
    }
}