package sistemarecomendacionpeliculas;

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

    public Usuario(int id, String nombre) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id del usuario debe ser positivo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
        }
        this.id = id;
        this.nombre = nombre.trim();
        this.generosPreferidos = new ArrayList<>();
        this.porVer = new LinkedList<>();
        this.historial = new Stack<>();
    }

    // ---------- Getters ----------
    public int getId()                             { return id; }
    public String getNombre()                      { return nombre; }
    public ArrayList<String> getGenerosPreferidos() { return generosPreferidos; }
    public Queue<Pelicula> getPorVer()             { return porVer; }
    public Stack<Pelicula> getHistorial()          { return historial; }

    // ---------- Géneros preferidos ----------
    public void agregarGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("El género no puede estar vacío.");
        }
        if (!prefiereGenero(genero)) {
            generosPreferidos.add(genero.trim());
        }
    }

    public boolean prefiereGenero(String genero) {
        for (String g : generosPreferidos) {
            if (g.equalsIgnoreCase(genero.trim())) {
                return true;
            }
        }
        return false;
    }

    // ---------- Utilidad para recomendaciones ----------
    /** Indica si el usuario ya vio la película (está en su historial). */
    public boolean yaVio(Pelicula p) {
        for (Pelicula vista : historial) {
            if (vista.getCodigo() == p.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Usuario %d: %s | Géneros: %s", id, nombre, generosPreferidos);
    }
}

