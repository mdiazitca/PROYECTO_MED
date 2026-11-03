
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
        if (ratingMinimo < 1 || ratingMinimo > 5) {
            throw new IllegalArgumentException(
                "El rating mínimo debe estar entre 1 y 5."
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
}