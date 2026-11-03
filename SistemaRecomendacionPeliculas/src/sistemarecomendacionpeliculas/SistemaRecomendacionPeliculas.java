package sistemarecomendacionpeliculas;

import java.util.Scanner;
import java.util.Queue;

public class SistemaRecomendacionPeliculas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // =================================
        // REGISTRO INICIAL DE PELÍCULAS
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
            new Pelicula(8, "En busca de la felicidad",
                    "Drama", 2006, 4.7));


        // =================================
        // REGISTRO INICIAL DE USUARIOS
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

        // Registrar película vista por Ana
        u1.registrarPeliculaVista(
                gestionPeliculas.buscarPorCodigo(1)
        );


        // =================================
        // CREAR ÁRBOL BST
        // =================================

        ArbolBST arbol = new ArbolBST();

        arbol.cargarDesdeLista(
                gestionPeliculas.getPeliculas()
        );

        SistemaRecomendaciones sistema =
                new SistemaRecomendaciones();


        // =================================
        // MENÚ PRINCIPAL
        // =================================

        int opcion;

        do {

            System.out.println("\n========================================");
            System.out.println(" SISTEMA DE RECOMENDACIÓN DE PELÍCULAS");
            System.out.println("========================================");
            System.out.println("1. Mostrar películas");
            System.out.println("2. Registrar película");
            System.out.println("3. Mostrar usuarios");
            System.out.println("4. Registrar usuario");
            System.out.println("5. Ordenar películas por rating");
            System.out.println("6. Ordenar películas por año");
            System.out.println("7. Buscar película por código");
            System.out.println("8. Buscar película por título");
            System.out.println("9. Agregar película a lista por ver");
            System.out.println("10. Mostrar películas pendientes");
            System.out.println("11. Marcar siguiente película como vista");
            System.out.println("12. Registrar película vista por código");
            System.out.println("13. Mostrar historial de películas vistas");
            System.out.println("14. Generar recomendaciones");
            System.out.println("15. Salir");
            System.out.println("========================================");

            opcion = leerEntero(sc, "Seleccione una opción: ");

            switch (opcion) {

                // =================================
                // 1. MOSTRAR PELÍCULAS
                // =================================

                case 1:

                    System.out.println("\n===== PELÍCULAS REGISTRADAS =====");
                    gestionPeliculas.mostrarPeliculas();

                    break;


                // =================================
                // 2. REGISTRAR PELÍCULA
                // =================================

                case 2:

                    try {

                        int codigo = leerEntero(sc, "Código: ");

                        System.out.print("Título: ");
                        String titulo = sc.nextLine();

                        System.out.print("Género: ");
                        String genero = sc.nextLine();

                        int anio = leerEntero(sc, "Año: ");

                        double rating = leerDouble(sc, "Rating (1 a 5): ");

                        // Comprobar título repetido en el BST
                        boolean existeTitulo = false;

                        try {
                            arbol.buscarPorTitulo(titulo);
                            existeTitulo = true;

                        } catch (PeliculaNoEncontradaException e) {
                            // El título todavía no existe
                        }

                        if (existeTitulo) {

                            System.out.println(
                                "Ya existe una película con ese título."
                            );

                        } else {

                            Pelicula nueva = new Pelicula(
                                    codigo, titulo, genero, anio, rating
                            );

                            gestionPeliculas.agregarPelicula(nueva);

                            // Actualizar el BST
                            arbol = new ArbolBST();

                            arbol.cargarDesdeLista(
                                    gestionPeliculas.getPeliculas()
                            );

                            System.out.println(
                                "Película registrada correctamente."
                            );
                        }

                    } catch (IllegalArgumentException e) {

                        System.out.println(
                            "Error: " + e.getMessage()
                        );
                    }

                    break;


                // =================================
                // 3. MOSTRAR USUARIOS
                // =================================

                case 3:

                    System.out.println("\n===== USUARIOS REGISTRADOS =====");
                    gestionUsuarios.mostrarUsuarios();

                    break;


                // =================================
                // 4. REGISTRAR USUARIO
                // =================================

                case 4:

                    try {

                        int id = leerEntero(sc, "ID del usuario: ");

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        Usuario nuevoUsuario =
                                new Usuario(id, nombre);

                        System.out.print(
                            "Géneros preferidos separados por coma: "
                        );

                        String entradaGeneros = sc.nextLine();

                        if (!entradaGeneros.trim().isEmpty()) {

                            String[] generos =
                                    entradaGeneros.split(",");

                            for (String genero : generos) {
                                nuevoUsuario.agregarGenero(genero);
                            }
                        }

                        gestionUsuarios.agregarUsuario(nuevoUsuario);

                        System.out.println(
                            "Usuario registrado correctamente."
                        );

                    } catch (IllegalArgumentException e) {

                        System.out.println(
                            "Error: " + e.getMessage()
                        );
                    }

                    break;


                // =================================
                // 5. ORDENAR POR RATING
                // =================================

                case 5:

                    System.out.println("\n===== ORDENADAS POR RATING =====");

                    for (Pelicula p :
                            gestionPeliculas.ordenarPorRating()) {

                        System.out.println(p);
                    }

                    break;


                // =================================
                // 6. ORDENAR POR AÑO
                // =================================

                case 6:

                    System.out.println("\n===== ORDENADAS POR AÑO =====");

                    for (Pelicula p :
                            gestionPeliculas.ordenarPorAnio()) {

                        System.out.println(p);
                    }

                    break;


                // =================================
                // 7. BÚSQUEDA BINARIA POR CÓDIGO
                // =================================

                case 7:

                    int codigoBuscar =
                            leerEntero(sc, "Ingrese el código: ");

                    Pelicula encontrada =
                            gestionPeliculas.buscarPorCodigoBinaria(
                                    codigoBuscar
                            );

                    if (encontrada != null) {

                        System.out.println("Película encontrada:");
                        System.out.println(encontrada);

                    } else {

                        System.out.println(
                            "No existe una película con ese código."
                        );
                    }

                    break;


                // =================================
                // 8. BÚSQUEDA POR TÍTULO EN BST
                // =================================

                case 8:

                    System.out.print("Ingrese el título: ");
                    String tituloBuscar = sc.nextLine();

                    try {

                        Pelicula resultado =
                                arbol.buscarPorTitulo(tituloBuscar);

                        System.out.println("Película encontrada:");
                        System.out.println(resultado);

                    } catch (PeliculaNoEncontradaException |
                             IllegalArgumentException e) {

                        System.out.println(
                            "Error: " + e.getMessage()
                        );
                    }

                    break;


                // =================================
                // 9. AGREGAR A COLA POR VER
                // =================================

                case 9:

                    int idCola =
                            leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioCola =
                            gestionUsuarios.buscarPorId(idCola);

                    if (usuarioCola == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    int codigoCola =
                            leerEntero(sc, "Código de película: ");

                    Pelicula peliculaCola =
                            gestionPeliculas.buscarPorCodigo(codigoCola);

                    if (peliculaCola == null) {

                        System.out.println("Película no encontrada.");

                        break;
                    }

                    try {

                        usuarioCola.agregarPorVer(peliculaCola);

                        System.out.println(
                            "Película agregada a la lista por ver."
                        );

                    } catch (IllegalArgumentException e) {

                        System.out.println(
                            "Error: " + e.getMessage()
                        );
                    }

                    break;


                // =================================
                // 10. MOSTRAR COLA
                // =================================

                case 10:

                    int idPendiente =
                            leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioPendiente =
                            gestionUsuarios.buscarPorId(idPendiente);

                    if (usuarioPendiente == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    System.out.println("\n===== PELÍCULAS POR VER =====");

                    Queue<Pelicula> cola =
                            usuarioPendiente.getPorVer();

                    if (cola.isEmpty()) {

                        System.out.println(
                            "No hay películas pendientes."
                        );

                    } else {

                        for (Pelicula p : cola) {
                            System.out.println(p);
                        }
                    }

                    break;


                // =================================
                // 11. MARCAR SIGUIENTE COMO VISTA
                // =================================

                case 11:

                    int idSiguiente =
                            leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioSiguiente =
                            gestionUsuarios.buscarPorId(idSiguiente);

                    if (usuarioSiguiente == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    Pelicula siguiente =
                            usuarioSiguiente.verSiguiente();

                    if (siguiente == null) {

                        System.out.println(
                            "No hay películas pendientes."
                        );

                    } else {

                        usuarioSiguiente.registrarPeliculaVista(siguiente);

                        System.out.println(
                            "Película registrada en el historial:"
                        );

                        System.out.println(siguiente);
                    }

                    break;


                // =================================
                // 12. REGISTRAR PELÍCULA VISTA
                // =================================

                case 12:

                    int idVista =
                            leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioVista =
                            gestionUsuarios.buscarPorId(idVista);

                    if (usuarioVista == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    int codigoVista =
                            leerEntero(sc, "Código de película: ");

                    Pelicula peliculaVista =
                            gestionPeliculas.buscarPorCodigo(codigoVista);

                    if (peliculaVista == null) {

                        System.out.println("Película no encontrada.");

                        break;
                    }

                    try {

                        usuarioVista.registrarPeliculaVista(
                                peliculaVista
                        );

                        System.out.println(
                            "Película agregada al historial."
                        );

                    } catch (IllegalArgumentException e) {

                        System.out.println(
                            "Error: " + e.getMessage()
                        );
                    }

                    break;


                // =================================
                // 13. MOSTRAR HISTORIAL
                // =================================

                case 13:

                    int idHistorial =
                            leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioHistorial =
                            gestionUsuarios.buscarPorId(idHistorial);

                    if (usuarioHistorial == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    System.out.println(
                        "\n===== HISTORIAL DE PELÍCULAS VISTAS ====="
                    );

                    if (usuarioHistorial.getHistorial().isEmpty()) {

                        System.out.println("El historial está vacío.");

                    } else {

                        // Mostrar primero la más reciente
                        for (int i =
                                usuarioHistorial.getHistorial().size() - 1;
                             i >= 0; i--) {

                            System.out.println(
                                usuarioHistorial.getHistorial().get(i)
                            );
                        }
                    }

                    break;


                // =================================
                // 14. GENERAR RECOMENDACIONES
                // =================================

                case 14:

                    int idRecomendacion =
                            leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioRecomendacion =
                            gestionUsuarios.buscarPorId(idRecomendacion);

                    if (usuarioRecomendacion == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    double minimo =
                            leerDouble(sc, "Rating mínimo (1 a 5): ");

                    try {

                        sistema.recomendarPeliculas(
                                usuarioRecomendacion,
                                arbol,
                                minimo
                        );

                    } catch (IllegalArgumentException e) {

                        System.out.println(
                            "Error: " + e.getMessage()
                        );
                    }

                    break;


                // =================================
                // 15. SALIR
                // =================================

                case 15:

                    System.out.println(
                        "Gracias por utilizar el sistema."
                    );

                    break;


                // =================================
                // OPCIÓN INCORRECTA
                // =================================

                default:

                    System.out.println(
                        "Opción inválida. Intente nuevamente."
                    );
            }

        } while (opcion != 15);

        sc.close();
    }


    // =================================
    // LEER NÚMEROS ENTEROS
    // =================================

    public static int leerEntero(
            Scanner sc, String mensaje) {

        while (true) {

            System.out.print(mensaje);

            try {

                return Integer.parseInt(sc.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println(
                    "Debe ingresar un número entero válido."
                );
            }
        }
    }


    // =================================
    // LEER NÚMEROS DECIMALES
    // =================================

    public static double leerDouble(
            Scanner sc, String mensaje) {

        while (true) {

            System.out.print(mensaje);

            try {

                return Double.parseDouble(
                        sc.nextLine().trim().replace(',', '.')
                );

            } catch (NumberFormatException e) {

                System.out.println(
                    "Debe ingresar un número válido."
                );
            }
        }
    }

}
