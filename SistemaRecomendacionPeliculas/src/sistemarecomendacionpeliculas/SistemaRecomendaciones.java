package sistemarecomendacionpeliculas;

import java.util.ArrayList;

public class SistemaRecomendaciones {

    // =====================================
    // OBTENER RECOMENDACIONES
    // 1. Recorrido recursivo del BST que filtra por
    //    género preferido, película no vista y rating mínimo.
    // 2. Ordena el resultado por rating (mayor a menor).
    // =====================================

    public ArrayList<Pelicula> obtenerRecomendaciones(
            Usuario usuario,
            ArbolBST arbol,
            double ratingMinimo) {

        if (usuario == null) {
            throw new IllegalArgumentException(
                "El usuario no puede ser nulo."
            );
        }

        if (arbol == null) {
            throw new IllegalArgumentException(
                "El árbol no puede ser nulo."
            );
        }

        if (!Double.isFinite(ratingMinimo)
                || ratingMinimo < 1 || ratingMinimo > 5) {
            throw new IllegalArgumentException(
                "El rating mínimo debe estar entre 1 y 5."
            );
        }

        // Recorrido recursivo del BST
        ArrayList<Pelicula> resultados
                = arbol.buscarRecomendaciones(usuario, ratingMinimo);

        // Mejor calificadas primero
        resultados.sort((a, b)
                -> Double.compare(b.getRating(), a.getRating())
        );

        return resultados;
    }

    // =====================================
    // MOSTRAR RECOMENDACIONES
    // =====================================

    public void recomendarPeliculas(
            Usuario usuario,
            ArbolBST arbol,
            double ratingMinimo) {

        ArrayList<Pelicula> recomendaciones
                = obtenerRecomendaciones(usuario, arbol, ratingMinimo);

        System.out.println("\n===== RECOMENDACIONES PARA "
                + usuario.getNombre() + " =====");

        if (recomendaciones.isEmpty()) {
            System.out.println(
                "No hay películas que cumplan los criterios."
            );
            return;
        }

        for (Pelicula p : recomendaciones) {
            System.out.println(p);
        }
    }
}
