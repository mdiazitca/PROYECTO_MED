
package sistemarecomendacionpeliculas;

import java.util.ArrayList;

public class SistemaRecomendaciones {

    // =====================================
    // GENERAR RECOMENDACIONES UTILIZANDO BST
    // =====================================

    public void recomendarPeliculas(
            Usuario usuario,
            ArbolBST arbol,
            double ratingMinimo) {

        // Validar usuario
        if (usuario == null) {
            throw new IllegalArgumentException(
                "El usuario no puede ser nulo."
            );
        }

        // Validar árbol
        if (arbol == null) {
            throw new IllegalArgumentException(
                "El árbol no puede ser nulo."
            );
        }

        // Validar rating mínimo
        if (ratingMinimo < 1 || ratingMinimo > 10) {
            throw new IllegalArgumentException(
                "El rating mínimo debe estar entre 1 y 10."
            );
            
        }

        System.out.println("\n===== RECOMENDACIONES PARA "
                + usuario.getNombre() + " =====");

        // Obtener películas mediante recorrido inorden del BST
        ArrayList<Pelicula> peliculas
                = arbol.recorridoInorden();
        
        // Ordenar recomendaciones por rating de mayor a menor
        peliculas.sort((a, b)
                -> Double.compare(b.getRating(), a.getRating())
        );

        // Verificar si existen películas
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas.");
            return;
        }

        // Generar recomendaciones recursivamente
        recomendarRecursivo(
                usuario,
                peliculas,
                ratingMinimo,
                0
        );
    }

    // =====================================
    // MÉTODO RECURSIVO DE RECOMENDACIONES
    // =====================================

    private void recomendarRecursivo(
            Usuario usuario,
            ArrayList<Pelicula> peliculas,
            double ratingMinimo,
            int indice) {

        // CASO BASE
        if (indice >= peliculas.size()) {
            return;
        }

        // Obtener película actual
        Pelicula pelicula = peliculas.get(indice);

        // Verificar condiciones de recomendación
        if (usuario.prefiereGenero(pelicula.getGenero())
                && !usuario.yaVio(pelicula)
                && pelicula.getRating() >= ratingMinimo) {

            System.out.println(pelicula);
        }

        // LLAMADA RECURSIVA
        recomendarRecursivo(
                usuario,
                peliculas,
                ratingMinimo,
                indice + 1
        );
    }
    public ArrayList<Pelicula> obtenerRecomendaciones(
        Usuario usuario,
        ArbolBST arbol,
        double ratingMinimo) {

    if (usuario == null || arbol == null) {
        throw new IllegalArgumentException(
            "El usuario y el árbol no pueden ser nulos."
        );
    }

    if (!Double.isFinite(ratingMinimo)
            || ratingMinimo < 1 || ratingMinimo > 10) {
        throw new IllegalArgumentException(
            "El rating mínimo debe estar entre 1 y 10."
        );
    }
//--------------------------------------------------------------------------------------------------------------------------
    ArrayList<Pelicula> peliculas = arbol.recorridoInorden();

    peliculas.sort((a, b) ->
        Double.compare(b.getRating(), a.getRating()));

    ArrayList<Pelicula> resultados = new ArrayList<>();

    obtenerRecomendacionesRecursivo(
        usuario, peliculas, ratingMinimo, 0, resultados);

    return resultados;
}

private void obtenerRecomendacionesRecursivo(
        Usuario usuario,
        ArrayList<Pelicula> peliculas,
        double ratingMinimo,
        int indice,
        ArrayList<Pelicula> resultados) {

    if (indice >= peliculas.size()) {
        return;
    }

    Pelicula pelicula = peliculas.get(indice);

    if (usuario.prefiereGenero(pelicula.getGenero())
            && !usuario.yaVio(pelicula)
            && pelicula.getRating() >= ratingMinimo) {

        resultados.add(pelicula);
    }

    obtenerRecomendacionesRecursivo(
        usuario, peliculas, ratingMinimo, indice + 1, resultados);
}
}