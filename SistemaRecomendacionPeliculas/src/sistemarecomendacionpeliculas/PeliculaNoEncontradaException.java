package sistemarecomendacionpeliculas;

/**
 * Excepción propia que se lanza cuando se busca una película que no existe.
 */
public class PeliculaNoEncontradaException extends Exception {

    public PeliculaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
