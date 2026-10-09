package Controlador;

import Modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {
    private SessionController session;

    @BeforeEach
    void setUp() {
        session = new SessionController();
    }

    @Test
    @DisplayName("Al iniciar, no hay ningún usuario en sesión")
    void inicial_sinSesion() {
        assertFalse(session.hayUsuario());
        assertNull(session.getUsuarioActual());
        assertEquals("", session.getNombreUsuario());
    }

    @Test
    @DisplayName("iniciarSesion con credenciales correctas abre la sesión")
    void iniciarSesion_credencialesCorrectas() {
        assertTrue(session.iniciarSesion("benjamin", "123"));
        assertTrue(session.hayUsuario());
        assertEquals("Benjamin", session.getNombreUsuario());
        assertEquals("benjamin", session.getUsuarioActual().getUsername());
    }

    @Test
    @DisplayName("iniciarSesion con clave incorrecta falla y no abre sesión")
    void iniciarSesion_claveIncorrecta() {
        assertFalse(session.iniciarSesion("benjamin", "mala"));
        assertFalse(session.hayUsuario());
    }

    @Test
    @DisplayName("iniciarSesion con un usuario inexistente falla")
    void iniciarSesion_usuarioInexistente() {
        assertFalse(session.iniciarSesion("fantasma", "123"));
        assertFalse(session.hayUsuario());
    }

    @Test
    @DisplayName("verificarUsuario indica si el usuario está registrado")
    void verificarUsuario_existenteEInexistente() {
        assertTrue(session.verificarUsuario("benjamin"));
        assertFalse(session.verificarUsuario("fantasma"));
    }

    @Test
    @DisplayName("registrarUsuario permite luego iniciar sesión con la nueva cuenta")
    void registrarUsuario_luegoInicioDeSesion() {
        session.registrarUsuario("ana", "pw", "Ana");
        assertTrue(session.verificarUsuario("ana"));
        assertTrue(session.iniciarSesion("ana", "pw"));
        assertEquals("Ana", session.getNombreUsuario());
    }

    @Test
    @DisplayName("registrarUsuario no inicia sesión automáticamente")
    void registrarUsuario_noIniciaSesion() {
        session.registrarUsuario("ana", "pw", "Ana");
        assertFalse(session.hayUsuario());
    }

    @Test
    @DisplayName("registrarUsuario con datos nulos lanza IllegalArgumentException")
    void registrarUsuario_datosNulos() {
        assertThrows(IllegalArgumentException.class, () -> session.registrarUsuario(null, "pw", "Ana"));
        assertFalse(session.verificarUsuario("ana"));
    }

    @Test
    @DisplayName("cambiarNombre actualiza el nombre del usuario en sesión")
    void cambiarNombre_valido() {
        session.iniciarSesion("benjamin", "123");
        session.cambiarNombre("Ben");
        assertEquals("Ben", session.getNombreUsuario());
    }

    @Test
    @DisplayName("cambiarNombre rechaza nombres vacíos o en blanco y conserva el anterior")
    void cambiarNombre_vacio() {
        session.iniciarSesion("benjamin", "123");
        assertThrows(IllegalArgumentException.class, () -> session.cambiarNombre(""));
        assertThrows(IllegalArgumentException.class, () -> session.cambiarNombre("   "));
        assertThrows(IllegalArgumentException.class, () -> session.cambiarNombre(null));
        assertEquals("Benjamin", session.getNombreUsuario());
    }

    @Test
    @DisplayName("cambiarNombre sin sesión activa lanza IllegalStateException")
    void cambiarNombre_sinSesion() {
        assertThrows(IllegalStateException.class, () -> session.cambiarNombre("Ben"));
    }

    @Test
    @DisplayName("cerrarSesion elimina al usuario actual")
    void cerrarSesion_limpiaElUsuario() {
        session.iniciarSesion("benjamin", "123");
        session.cerrarSesion();
        assertFalse(session.hayUsuario());
        assertNull(session.getUsuarioActual());
        assertEquals("", session.getNombreUsuario());
    }

    @Test
    @DisplayName("Un nombre cambiado persiste al cerrar y volver a abrir sesión")
    void cambiarNombre_persisteEntreSesiones() {
        session.iniciarSesion("benjamin", "123");
        session.cambiarNombre("Ben");
        session.cerrarSesion();
        session.iniciarSesion("benjamin", "123");
        assertEquals("Ben", session.getNombreUsuario());
    }

    @Test
    @DisplayName("El constructor acepta un UsuarioManager inyectado")
    void constructorConManager_usaEseManager() {
        Modelo.UsuarioManager manager = new Modelo.UsuarioManager();
        manager.agregarUsuario("zoe", "pw", "Zoe");
        SessionController s = new SessionController(manager);
        assertTrue(s.iniciarSesion("zoe", "pw"));
        Usuario actual = s.getUsuarioActual();
        assertSame(manager.buscarUsuario("zoe"), actual);
    }
}