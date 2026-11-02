
package sistemarecomendacionpeliculas;

public class SistemaRecomendacionPeliculas {

    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(
            System.out, true,
            java.nio.charset.StandardCharsets.UTF_8
        ));

        // =================================
        // REGISTRO DE PELÍCULAS
        // =================================

        GestionPeliculas gestionPeliculas =
                new GestionPeliculas();

        gestionPeliculas.agregarPelicula(
            new Pelicula(1, "Interestelar", "Ciencia ficción", 2014, 4.9));

        gestionPeliculas.agregarPelicula(
            new Pelicula(2, "Titanic", "Romance", 1997, 4.5));

        gestionPeliculas.agregarPelicula(
            new Pelicula(3, "El origen", "Ciencia ficción", 2010, 4.8));

        gestionPeliculas.agregarPelicula(
            new Pelicula(4, "Toy Story", "Animación", 1995, 4.6));

        gestionPeliculas.agregarPelicula(
            new Pelicula(5, "Avatar", "Ciencia ficción", 2009, 4.7));

        gestionPeliculas.agregarPelicula(
            new Pelicula(6, "Coco", "Animación", 2017, 4.8));

        gestionPeliculas.agregarPelicula(
            new Pelicula(7, "El conjuro", "Terror", 2013, 4.3));

        gestionPeliculas.agregarPelicula(
            new Pelicula(8, "En busca de la felicidad", "Drama", 2006, 4.7));


        // =================================
        // MOSTRAR PELÍCULAS REGISTRADAS
        // =================================

        System.out.println("===== PELÍCULAS REGISTRADAS =====");

        gestionPeliculas.mostrarPeliculas();


        // =================================
        // ORDENAMIENTO POR RATING
        // BUBBLE SORT
        // =================================

        System.out.println("\n===== PELÍCULAS ORDENADAS POR RATING =====");

        for (Pelicula p : gestionPeliculas.ordenarPorRating()) {
            System.out.println(p);
        }


        // =================================
        // ORDENAMIENTO POR AÑO
        // SELECTION SORT
        // =================================

        System.out.println("\n===== PELÍCULAS ORDENADAS POR AÑO =====");

        for (Pelicula p : gestionPeliculas.ordenarPorAnio()) {
            System.out.println(p);
        }


        // =================================
        // BÚSQUEDA BINARIA POR CÓDIGO
        // =================================

        System.out.println("\n===== BÚSQUEDA BINARIA =====");

        int codigoBuscar = 5;

        Pelicula encontrada =
                gestionPeliculas.buscarPorCodigoBinaria(codigoBuscar);

        if (encontrada != null) {

            System.out.println("Película encontrada:");
            System.out.println(encontrada);

        } else {

            System.out.println("No existe una película con ese código.");
        }


        // =================================
        // REGISTRO DE USUARIOS
        // =================================

        GestionUsuarios gestionUsuarios =
                new GestionUsuarios();

        Usuario u1 = new Usuario(1, "Ana");
        Usuario u2 = new Usuario(2, "Carlos");

        u1.agregarGenero("Ciencia ficción");
        u1.agregarGenero("Animación");

        u2.agregarGenero("Terror");
        u2.agregarGenero("Drama");

        gestionUsuarios.agregarUsuario(u1);
        gestionUsuarios.agregarUsuario(u2);


        // =================================
        // MOSTRAR USUARIOS
        // =================================

        System.out.println("\n===== USUARIOS REGISTRADOS =====");

        gestionUsuarios.mostrarUsuarios();

    }
}
