package Controlador;

import Modelo.Resultado;
import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ResultadoController {
    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public List<String> obtenerHistorial() {
        Usuario usuario = session.getUsuarioActual();
        if (usuario == null) {
            throw new IllegalStateException("El usuario no existe.");
        }
        List<Resultado> historial = usuario.getHistorial();
        List<String> descripciones = new ArrayList<>();
        for (Resultado resultado : historial) {
            descripciones.add(resultado.toString());
        }
        return descripciones;
    }
}
