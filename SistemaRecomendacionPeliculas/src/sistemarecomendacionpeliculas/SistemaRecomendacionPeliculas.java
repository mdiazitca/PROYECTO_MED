

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

        // REGISTRAR PELÍCULA VISTA POR ANA
        u1.registrarPeliculaVista(
                gestionPeliculas.buscarPorCodigo(1)
        );


        // =================================
        // MOSTRAR USUARIOS
        // =================================

        System.out.println("\n===== USUARIOS REGISTRADOS =====");

        gestionUsuarios.mostrarUsuarios();


        // =================================
        // CREAR Y CARGAR EL ÁRBOL BST
        // =================================

        ArbolBST arbol = new ArbolBST();

        arbol.cargarDesdeLista(
                gestionPeliculas.getPeliculas()
        );

        System.out.println("\n===== PELÍCULAS EN EL ÁRBOL BST =====");

        arbol.mostrarInorden();


        // =================================
        // SISTEMA DE RECOMENDACIONES
        // =================================

        SistemaRecomendaciones sistema =
                new SistemaRecomendaciones();

        // Recomendaciones para Ana
        sistema.recomendarPeliculas(u1, arbol, 4.5);

        // Recomendaciones para Carlos
        sistema.recomendarPeliculas(u2, arbol, 4.5);


        // =================================
        // PRUEBAS DE COLA Y PILA
        // =================================

        // Usuario independiente para las pruebas
        Usuario prueba = new Usuario(3, "Usuario de prueba");


        // =================================
        // PRUEBA DE COLA (QUEUE)
        // FIFO: PRIMERO EN ENTRAR,
        // PRIMERO EN SALIR
        // =================================

        System.out.println("\n===== PRUEBA DE COLA (QUEUE) =====");

        // Agregar películas a la cola
        prueba.agregarPorVer(
                gestionPeliculas.buscarPorCodigo(2)
        );

        prueba.agregarPorVer(
                gestionPeliculas.buscarPorCodigo(3)
        );

        prueba.agregarPorVer(
                gestionPeliculas.buscarPorCodigo(5)
        );

        // Retirar películas en orden de llegada
        System.out.println("Primera película en la cola:");
        System.out.println(prueba.verSiguiente());

        System.out.println("Segunda película en la cola:");
        System.out.println(prueba.verSiguiente());

        System.out.println("Tercera película en la cola:");
        System.out.println(prueba.verSiguiente());


        // =================================
        // PRUEBA DE PILA (STACK)
        // LIFO: ÚLTIMO EN ENTRAR,
        // PRIMERO EN SALIR
        // =================================

        System.out.println("\n===== PRUEBA DE PILA (STACK) =====");

        // Registrar películas como vistas
        prueba.registrarPeliculaVista(
                gestionPeliculas.buscarPorCodigo(1)
        );

        prueba.registrarPeliculaVista(
                gestionPeliculas.buscarPorCodigo(4)
        );

        prueba.registrarPeliculaVista(
                gestionPeliculas.buscarPorCodigo(6)
        );

        // Consultar la última película vista
        System.out.println("Última película registrada como vista:");

        System.out.println(prueba.obtenerUltimaVista());


        // =================================
        // PRUEBAS DE MANEJO DE EXCEPCIONES
        // =================================

        System.out.println("\n===== PRUEBAS DE EXCEPCIONES =====");

        // PRUEBA 1: Código repetido
        try {
            gestionPeliculas.agregarPelicula(
                new Pelicula(1, "Avatar 2",
                        "Ciencia ficción", 2022, 4.5)
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        // PRUEBA 2: Rating fuera del rango
        try {
            Pelicula peliculaIncorrecta =
                new Pelicula(9, "Prueba", "Drama", 2020, 6.0);

            gestionPeliculas.agregarPelicula(peliculaIncorrecta);

        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        // PRUEBA 3: Título vacío
        try {
            Pelicula peliculaIncorrecta =
                new Pelicula(10, "", "Drama", 2020, 4.5);

            gestionPeliculas.agregarPelicula(peliculaIncorrecta);

        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        // PRUEBA 4: Usuario con ID repetido
        try {
            Usuario usuarioIncorrecto =
                    new Usuario(1, "Pedro");

            gestionUsuarios.agregarUsuario(usuarioIncorrecto);

        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        // PRUEBA 5: Usuario con nombre vacío
        try {
            Usuario usuarioIncorrecto =
                    new Usuario(3, "");

            gestionUsuarios.agregarUsuario(usuarioIncorrecto);

        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

    }
}
