/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarecomendacionpeliculas;

/**
 *
 * @author aleyd
 */


import java.util.ArrayList;

public class SistemaRecomendaciones {

    // Método para generar recomendaciones
    public void recomendarPeliculas(
            Usuario usuario,
            ArrayList<Pelicula> peliculas,
            double ratingMinimo) {

        if (usuario == null) {
            throw new IllegalArgumentException(
                "El usuario no puede ser nulo."
            );
        }

        if (peliculas == null) {
            throw new IllegalArgumentException(
                "La lista de películas no puede ser nula."
            );
        }

        if (ratingMinimo < 1 || ratingMinimo > 5) {
            throw new IllegalArgumentException(
                "El rating mínimo debe estar entre 1 y 5."
            );
        }

        System.out.println("\n===== RECOMENDACIONES PARA "
                + usuario.getNombre() + " =====");

        recomendarRecursivo(usuario, peliculas, ratingMinimo, 0);
    }

    // Método recursivo
    private void recomendarRecursivo(
            Usuario usuario,
            ArrayList<Pelicula> peliculas,
            double ratingMinimo,
            int indice) {

        // Caso base
        if (indice >= peliculas.size()) {
            return;
        }

        Pelicula pelicula = peliculas.get(indice);

        // Verificar si cumple las condiciones
        if (usuario.prefiereGenero(pelicula.getGenero())
                && !usuario.yaVio(pelicula)
                && pelicula.getRating() >= ratingMinimo) {

            System.out.println(pelicula);
        }

        // Llamada recursiva
        recomendarRecursivo(
                usuario, peliculas, ratingMinimo, indice + 1
        );
    }
}

