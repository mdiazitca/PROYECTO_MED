package sistemarecomendacionpeliculas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Guarda y carga los usuarios en un archivo de texto.
 *
 * Cada línea es un usuario con 5 campos separados por tabulación:
 *   id | nombre | géneros | códigos por ver | códigos del historial
 *
 * - El nombre y cada género se guardan en Base64 para que las tildes,
 *   comas o espacios no rompan el formato.
 * - La lista por ver se guarda en el orden de la cola (primero el que se atiende primero).
 * - El historial se guarda desde la película más antigua hasta la más reciente,
 *   para que al cargarlo la pila quede en el mismo orden.
 */
public class ArchivoUsuarios {

    // =====================================
    // GUARDAR USUARIOS EN UN ARCHIVO
    // =====================================

    public void guardarUsuarios(
            ArrayList<Usuario> usuarios, String nombreArchivo)
            throws IOException {

        Path ruta = Paths.get(nombreArchivo);

        try (BufferedWriter escritor = Files.newBufferedWriter(
                ruta, StandardCharsets.UTF_8)) {

            for (Usuario usuario : usuarios) {

                String nombre = codificar(usuario.getNombre());

                // Géneros: cada uno en Base64, separados por coma
                ArrayList<String> generos = new ArrayList<>();
                for (String genero : usuario.getGenerosPreferidos()) {
                    generos.add(codificar(genero));
                }

                // Cola por ver: códigos en el orden de la cola
                ArrayList<String> porVer = new ArrayList<>();
                for (Pelicula p : usuario.getPorVer()) {
                    porVer.add(String.valueOf(p.getCodigo()));
                }

                // Pila del historial: de la más antigua a la más reciente
                ArrayList<String> historial = new ArrayList<>();
                for (Pelicula p : usuario.getHistorial()) {
                    historial.add(String.valueOf(p.getCodigo()));
                }

                escritor.write(
                        usuario.getId() + "\t"
                        + nombre + "\t"
                        + String.join(",", generos) + "\t"
                        + String.join(",", porVer) + "\t"
                        + String.join(",", historial)
                );

                escritor.newLine();
            }
        }
    }

    // =====================================
    // CARGAR USUARIOS DESDE EL ARCHIVO
    // Necesita el catálogo para convertir los códigos en películas.
    // =====================================

    public ArrayList<Usuario> cargarUsuarios(
            String nombreArchivo, GestionPeliculas catalogo)
            throws IOException {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        Path ruta = Paths.get(nombreArchivo);

        if (!Files.exists(ruta)) {
            return usuarios;
        }

        try (BufferedReader lector = Files.newBufferedReader(
                ruta, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split("\t", -1);

                if (datos.length != 5) {
                    throw new IOException(
                            "Formato incorrecto en el archivo de usuarios.");
                }

                try {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = decodificar(datos[1]);

                    Usuario usuario = new Usuario(id, nombre);

                    for (String genero : separar(datos[2])) {
                        usuario.agregarGenero(decodificar(genero));
                    }

                    // Primero el historial (pila), en el orden en que se vieron
                    for (String codigo : separar(datos[4])) {
                        Pelicula p = catalogo.buscarPorCodigo(Integer.parseInt(codigo));
                        if (p != null) {
                            usuario.registrarPeliculaVista(p);
                        }
                    }

                    // Después la lista por ver (cola), en su orden
                    for (String codigo : separar(datos[3])) {
                        Pelicula p = catalogo.buscarPorCodigo(Integer.parseInt(codigo));
                        if (p != null) {
                            usuario.agregarPorVer(p);
                        }
                    }

                    usuarios.add(usuario);

                } catch (IllegalArgumentException e) {
                    throw new IOException(
                            "Hay datos inválidos en el archivo de usuarios.", e);
                }
            }
        }

        return usuarios;
    }

    // =====================================
    // MÉTODOS DE APOYO
    // =====================================

    private String codificar(String texto) {
        return Base64.getEncoder().encodeToString(
                texto.getBytes(StandardCharsets.UTF_8));
    }

    private String decodificar(String texto) {
        return new String(
                Base64.getDecoder().decode(texto), StandardCharsets.UTF_8);
    }

    // Separa una lista "a,b,c"; si está vacía devuelve un arreglo vacío
    private String[] separar(String campo) {
        if (campo.trim().isEmpty()) {
            return new String[0];
        }
        return campo.split(",");
    }
}
