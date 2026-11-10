
package sistemarecomendacionpeliculas;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Usuario {

    private int id;
    private String nombre;
    private ArrayList<String> generosPreferidos;
    private Queue<Pelicula> porVer;
    private Stack<Pelicula> historial;

    // Constructor
    public Usuario(int id, String nombre) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                "El id del usuario debe ser positivo."
            );
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre del usuario no puede estar vacío."
            );
        }

        this.id = id;
        this.nombre = nombre.trim();
        this.generosPreferidos = new ArrayList<>();
        this.porVer = new LinkedList<>();
        this.historial = new Stack<>();
    }

    // =====================================
    // GETTERS
    // =====================================

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getGenerosPreferidos() {
        return generosPreferidos;
    }

    public Queue<Pelicula> getPorVer() {
        return porVer;
    }

    public Stack<Pelicula> getHistorial() {
        return historial;
    }

    // =====================================
    // GÉNEROS PREFERIDOS
    // =====================================

    public void agregarGenero(String genero) {

        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El género no puede estar vacío."
            );
        }

        if (!prefiereGenero(genero)) {
            generosPreferidos.add(genero.trim());
        }
    }

    public boolean prefiereGenero(String genero) {

        if (genero == null) {
            return false;
        }

        String buscado = normalizar(genero);

        for (String g : generosPreferidos) {
            if (normalizar(g).equals(buscado)) {
                return true;
            }
        }

        return false;
    }

    // Quita tildes, espacios extra y mayúsculas para comparar géneros
    // Ej.: "Animacion" y "Animación" se consideran el mismo género
    private static String normalizar(String texto) {

        String sinTildes = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return sinTildes.toLowerCase();
    }

    // =====================================
    // UTILIDAD PARA RECOMENDACIONES
    // =====================================

    // Indica si el usuario ya vio la película
    public boolean yaVio(Pelicula p) {

        if (p == null) {
            return false;
        }

        for (Pelicula vista : historial) {
            if (vista.getCodigo() == p.getCodigo()) {
                return true;
            }
        }

        return false;
    }

    // =====================================
    // COLA: PELÍCULAS POR VER
    // =====================================

    // Agregar película a la cola
    public void agregarPorVer(Pelicula pelicula) {

        if (pelicula == null) {
            throw new IllegalArgumentException(
                "La película no puede ser nula."
            );
        }

        if (yaVio(pelicula)) {
            throw new IllegalArgumentException(
                "La película ya fue vista."
            );
        }

        for (Pelicula pendiente : porVer) {
            if (pendiente.getCodigo() == pelicula.getCodigo()) {
                throw new IllegalArgumentException(
                    "La película ya está en la lista por ver."
                );
            }
        }

        porVer.offer(pelicula);
    }

    // Retirar la primera película de la cola
    public Pelicula verSiguiente() {
        return porVer.poll();
    }

    // =====================================
    // PILA: HISTORIAL DE PELÍCULAS VISTAS
    // =====================================

    // Registrar película vista
    public void registrarPeliculaVista(Pelicula pelicula) {

        if (pelicula == null) {
            throw new IllegalArgumentException(
                "La película no puede ser nula."
            );
        }

        if (yaVio(pelicula)) {
            throw new IllegalArgumentException(
                "La película ya está registrada en el historial."
            );
        }

        porVer.removeIf(
            pendiente -> pendiente.getCodigo() == pelicula.getCodigo()
        );

        historial.push(pelicula);
    }

    // Consultar la última película vista
    public Pelicula obtenerUltimaVista() {

        if (historial.isEmpty()) {
            return null;
        }

        return historial.peek();
    }

    // =====================================
    // MOSTRAR INFORMACIÓN DEL USUARIO
    // =====================================

    @Override
    public String toString() {

        return String.format(
            "Usuario %d: %s | Géneros: %s",
            id, nombre, generosPreferidos
        );
    }
}
