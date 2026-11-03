package sistemarecomendacionpeliculas;

/**
 * Nodo del árbol binario de búsqueda.
 * Guarda una película y la referencia a sus dos hijos.
 */
public class NodoBST {

    Pelicula pelicula;
    NodoBST izquierdo;
    NodoBST derecho;

    public NodoBST(Pelicula pelicula) {
        this.pelicula = pelicula;
        this.izquierdo = null;
        this.derecho = null;
    }
}
