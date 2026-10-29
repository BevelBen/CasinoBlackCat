package Modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersistenciaUsuarios {
    private final String SEP = ":";
    private final Path ruta;

    public PersistenciaUsuarios(Path ruta) {
        this.ruta = ruta;
    }

    /**
     * Verifica que aún exista la ruta donde se guardan los datos de los usuarios.
     * @return true si existe, false en caso contrario.
     */
    public boolean existeRuta() {
        return Files.exists(ruta);
    }

    /**
     * Se encarga de cargar los datos a partir del archivo en la ruta especificada, el formato de los archivos son:
     * U : username : password : nombre : saldo (En caso de usuario)
     * H : número obtenido : etiqueta apuesta : monto apostado : acierto (En caso de una jugada del usuario anterior)
     * @return la lista de usuarios obtenida desde el archivo.
     */
    public List<Usuario> cargar() {
        List<Usuario> usuarios = new ArrayList<>();
        if (!existeRuta()) {
            guardar(usuarios);
            return usuarios;
        }

        List<String> lineas = obtenerLineas();

        Usuario usuarioActual = null;
        for (String linea : lineas) {
            String[] campos = linea.split(SEP);
            switch (campos[0]) {
                case "U":
                    usuarioActual = new Usuario(campos[1], campos[2], campos[3], Integer.parseInt(campos[4]));
                    usuarios.add(usuarioActual);
                    break;
                case "H":
                    usuarioActual.registrarJugada(new Resultado(Integer.parseInt(campos[1]), campos[2], Integer.parseInt(campos[3]), parsearBoolean(campos[4])));
                    break;
            }
        }
        return usuarios;
    }

    /**
     * Permite rescatar las líneas desde el archivo de los usuarios.
     * @return una lista de las líneas.
     */
    private List<String> obtenerLineas() {
        try {
            return Files.readAllLines(ruta, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer el archivo de datos: " + ruta, e);
        }
    }

    /**
     * Guarda una lista de usuarios en el archivo en la ruta especificada, el formato del archivo es:
     * U : username : password : nombre : saldo (En caso de usuario)
     * H : número obtenido : etiqueta apuesta : monto apostado : acierto (En caso de una jugada del usuario anterior)
     * @param usuarios la lista de usuarios a guardar.
     */
    public void guardar(Collection<Usuario> usuarios) {
        List<String> lineas = new ArrayList<>();
        for (Usuario u : usuarios) {
            lineas.add(String.join(SEP, "U", u.getUsername(), u.getPassword(), u.getNombre(), String.valueOf(u.getSaldo())));
            for (Resultado r : u.getHistorial()) {
                lineas.add(String.join(SEP, "H", String.valueOf(r.getNumeroRuleta()), r.getEtiquetaApuesta(), String.valueOf(r.getMonto()), String.valueOf(r.getAcerto())));
            }
        }

        try {
            Path carpeta = ruta.toAbsolutePath().getParent();
            if (carpeta != null) {
                Files.createDirectories(carpeta);
            }
            Files.write(ruta, lineas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo guardar el archivo de datos: " + ruta, e);
        }
    }

    /**
     * Convierte un valor booleano en forma de cadena a valor booleano.
     * @param bool el booleano en forma de cadena.
     * @return el booleano en forma de booleano.
     */
    private boolean parsearBoolean(String bool) {
        if (bool.equals("true")) {
            return true;
        }
        if (bool.equals("false")) {
            return false;
        }
        throw new IllegalArgumentException("Valor de booleano invalido.");
    }
}
