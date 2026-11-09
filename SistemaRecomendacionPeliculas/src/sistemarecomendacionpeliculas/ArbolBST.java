package sistemarecomendacionpeliculas;

import java.util.ArrayList;

/**
 * Árbol Binario de Búsqueda (BST) de películas, ordenado por TÍTULO.
 * - Los títulos menores (alfabéticamente) van a la izquierda.
 * - Los títulos mayores van a la derecha.
 * La comparación no distingue mayúsculas de minúsculas.
 * Todas las operaciones se hacen de forma recursiva.
 */
public class ArbolBST {

    private NodoBST raiz;
    private int tamanio;

    // Constructor
    public ArbolBST() {
        raiz = null;
        tamanio = 0;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    // =====================================
    // CARGAR PELÍCULAS DESDE UNA LISTA
    // (por ejemplo, gestionPeliculas.getPeliculas())
    // =====================================

     public void cargarDesdeLista(ArrayList<Pelicula> lista) {

    if (lista == null) {
        throw new IllegalArgumentException(
            "La lista de películas no puede ser nula."
        );
    }

    for (Pelicula p : lista) {
        insertar(p);
    }
}

    // =====================================
    // INSERTAR
    // =====================================

    public void insertar(Pelicula pelicula) {

        if (pelicula == null) {
            throw new IllegalArgumentException(
                "No se puede insertar una película nula."
            );
        }

        raiz = insertarRecursivo(raiz, pelicula);
    }

    private NodoBST insertarRecursivo(NodoBST nodo, Pelicula pelicula) {

        // Caso base: lugar vacío, aquí va la película
        if (nodo == null) {
            tamanio++;
            return new NodoBST(pelicula);
        }

        int comparacion = pelicula.getTitulo()
                .compareToIgnoreCase(nodo.pelicula.getTitulo());

        if (comparacion < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, pelicula);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, pelicula);
        } else {
            throw new IllegalArgumentException(
                "Ya existe una película con el título: " + pelicula.getTitulo()
            );
        }

        return nodo;
    }

    // =====================================
    // BUSCAR POR TÍTULO
    // =====================================

    public Pelicula buscarPorTitulo(String titulo)
            throws PeliculaNoEncontradaException {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El título a buscar no puede estar vacío."
            );
        }

        NodoBST encontrado = buscarRecursivo(raiz, titulo.trim());

        if (encontrado == null) {
            throw new PeliculaNoEncontradaException(
                "No existe una película con el título: " + titulo
            );
        }

        return encontrado.pelicula;
    }

    private NodoBST buscarRecursivo(NodoBST nodo, String titulo) {

        // Caso base: no se encontró
        if (nodo == null) {
            return null;
        }

        int comparacion = titulo.compareToIgnoreCase(nodo.pelicula.getTitulo());

        if (comparacion == 0) {
            return nodo;                                   // encontrada
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.izquierdo, titulo); // ir a la izquierda
        } else {
            return buscarRecursivo(nodo.derecho, titulo);   // ir a la derecha
        }
    }

    // =====================================
    // BÚSQUEDA PARCIAL POR TÍTULO
    // Permite encontrar varias películas
    // =====================================-------------------------------------------------------------------------------

    public ArrayList<Pelicula> buscarPorCoincidencia(String texto) {

        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Debe ingresar una palabra para buscar."
            );
        }

        ArrayList<Pelicula> resultados = new ArrayList<>();

        String palabra = texto.trim().toLowerCase();

        // Recorrer todas las películas del árbol
        for (Pelicula p : recorridoInorden()) {

            String titulo = p.getTitulo().toLowerCase();

            if (titulo.contains(palabra)) {
                resultados.add(p);
            }
        }

        return resultados;
    }
//---------------------------------------------------------------------------------------------------------------
    // =====================================
    // RECORRIDO IN-ORDEN
    // Devuelve las películas en orden alfabético
    // =====================================

    public ArrayList<Pelicula> recorridoInorden() {

        ArrayList<Pelicula> lista = new ArrayList<>();
        inordenRecursivo(raiz, lista);
        return lista;
    }

    private void inordenRecursivo(NodoBST nodo, ArrayList<Pelicula> lista) {

        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo, lista);
            lista.add(nodo.pelicula);
            inordenRecursivo(nodo.derecho, lista);
        }
    }

    // =====================================
    // RECOMENDACIONES: RECORRIDO RECURSIVO
    // Recorre el árbol (izquierda, nodo, derecha) y guarda
    // las películas que cumplen el género preferido del
    // usuario, que no ha visto y con rating mínimo.
    // =====================================

    public ArrayList<Pelicula> buscarRecomendaciones(
            Usuario usuario, double ratingMinimo) {

        ArrayList<Pelicula> resultados = new ArrayList<>();
        recomendacionesRecursivo(raiz, usuario, ratingMinimo, resultados);
        return resultados;
    }

    private void recomendacionesRecursivo(
            NodoBST nodo,
            Usuario usuario,
            double ratingMinimo,
            ArrayList<Pelicula> resultados) {

        // Caso base: nodo vacío
        if (nodo == null) {
            return;
        }

        // Subárbol izquierdo
        recomendacionesRecursivo(nodo.izquierdo, usuario, ratingMinimo, resultados);

        // Nodo actual
        Pelicula p = nodo.pelicula;

        if (usuario.prefiereGenero(p.getGenero())
                && !usuario.yaVio(p)
                && p.getRating() >= ratingMinimo) {

            resultados.add(p);
        }

        // Subárbol derecho
        recomendacionesRecursivo(nodo.derecho, usuario, ratingMinimo, resultados);
    }

    public void mostrarInorden() {

        if (estaVacio()) {
            System.out.println("El árbol está vacío.");
            return;
        }

        for (Pelicula p : recorridoInorden()) {
            System.out.println(p);
        }
    }
}
