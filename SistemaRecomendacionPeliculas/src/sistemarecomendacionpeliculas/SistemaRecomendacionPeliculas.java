
package sistemarecomendacionpeliculas;

public class SistemaRecomendacionPeliculas {

    public static void main(String[] args) {
            System.setOut(new java.io.PrintStream(
        System.out, true, java.nio.charset.StandardCharsets.UTF_8
    ));


        // REGISTRO DE PELÍCULAS
        Pelicula p1 = new Pelicula(1, "Interestelar", "Ciencia ficción", 2014, 4.9);
        Pelicula p2 = new Pelicula(2, "Titanic", "Romance", 1997, 4.5);
        Pelicula p3 = new Pelicula(3, "El origen", "Ciencia ficción", 2010, 4.8);
        Pelicula p4 = new Pelicula(4, "Toy Story", "Animación", 1995, 4.6);
        Pelicula p5 = new Pelicula(5, "Avatar", "Ciencia ficción", 2009, 4.7);
        Pelicula p6 = new Pelicula(6, "Coco", "Animación", 2017, 4.8);
        Pelicula p7 = new Pelicula(7, "El conjuro", "Terror", 2013, 4.3);
        Pelicula p8 = new Pelicula(8, "En busca de la felicidad", "Drama", 2006, 4.7);

        // MOSTRAR PELÍCULAS
        System.out.println("===== PELÍCULAS REGISTRADAS =====");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);
        System.out.println(p8);

        // REGISTRO DE USUARIOS
        Usuario u1 = new Usuario(1, "Ana");
        Usuario u2 = new Usuario(2, "Carlos");

        // GÉNEROS PREFERIDOS
        u1.agregarGenero("Ciencia ficción");
        u1.agregarGenero("Animación");

        u2.agregarGenero("Terror");
        u2.agregarGenero("Drama");

        // MOSTRAR USUARIOS
        System.out.println("\n===== USUARIOS REGISTRADOS =====");
        System.out.println(u1);
        System.out.println(u2);
    }
}
