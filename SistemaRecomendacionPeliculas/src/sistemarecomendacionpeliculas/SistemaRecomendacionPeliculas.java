package sistemarecomendacionpeliculas;

import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayList;

public class SistemaRecomendacionPeliculas {

    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(
                new java.io.FileOutputStream(
                        java.io.FileDescriptor.out
                ),
                true,
                java.nio.charset.StandardCharsets.UTF_8
        ));

        Scanner sc = new Scanner(System.in);

        // =================================
        // REGISTRO INICIAL DE PELÍCULAS
        // =================================
        GestionPeliculas gestionPeliculas
                = new GestionPeliculas();
        ArchivoPeliculas archivoPeliculas = new ArchivoPeliculas();

        String nombreArchivo = "peliculas.txt";

        try {

            ArrayList<Pelicula> peliculasGuardadas
                    = archivoPeliculas.cargarPeliculas(nombreArchivo);

            if (!peliculasGuardadas.isEmpty()) {

                for (Pelicula pelicula : peliculasGuardadas) {
                    gestionPeliculas.agregarPelicula(pelicula);
                }

                System.out.println("Películas cargadas correctamente.");

            } else {

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

                gestionPeliculas.agregarPelicula(
                        new Pelicula(9, "Cenicienta", "Animación", 2016, 3.7));

                gestionPeliculas.agregarPelicula(
                        new Pelicula(10, "Cenicienta II", "Animación", 2021, 3.2));

                gestionPeliculas.agregarPelicula(
                        new Pelicula(11, "Cenicienta III", "Animación", 2025, 4.1));

                gestionPeliculas.agregarPelicula(
                        new Pelicula(12, "Bambi", "Animación", 2025, 3.1));

                gestionPeliculas.agregarPelicula(
                        new Pelicula(13, "Bambi II", "Animación", 2025, 2.1));

                archivoPeliculas.guardarPeliculas(
                        gestionPeliculas.getPeliculas(), nombreArchivo);

                System.out.println("Catálogo inicial guardado.");

            }

        } catch (java.io.IOException e) {

            System.out.println("Error al cargar o guardar películas: " + e.getMessage());
        }

        // =================================
        // REGISTRO INICIAL DE USUARIOS
        // =================================
        GestionUsuarios gestionUsuarios
                = new GestionUsuarios();

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

        SistemaRecomendaciones sistema
                = new SistemaRecomendaciones();

        //------------------------------------------------------------------------------------
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
                    gestionPeliculas.mostrarPeliculasOrdenadasPorTitulo();
                    break;

                // =================================
                // 2. REGISTRAR PELÍCULA
                // =================================
                case 2:

                    String continuar;

                    do {

                        try {

                            int ultimoCodigo
                                    = gestionPeliculas.obtenerUltimoCodigoUtilizado();

                            System.out.println("\nÚltimo código utilizado: " + ultimoCodigo);

                            int codigo = leerEntero(
                                    sc, "Ingrese el código de la nueva película: ");

                            System.out.print("Título: ");
                            String titulo = sc.nextLine();

                            System.out.print("Género: ");
                            String genero = sc.nextLine();

                            int anio = leerEntero(sc, "Año: ");

                            double rating = leerDouble(sc, "Rating (1 a 5): ");

                            Pelicula nueva = new Pelicula(
                                    codigo, titulo, genero, anio, rating
                            );

                            gestionPeliculas.agregarPelicula(nueva);

// Guardar automáticamente todas las películas
                            try {

                                archivoPeliculas.guardarPeliculas(
                                        gestionPeliculas.getPeliculas(),
                                        nombreArchivo
                                );

                                System.out.println("Catálogo guardado correctamente.");

                            } catch (java.io.IOException e) {

                                System.out.println(
                                        "Error al guardar las películas: " + e.getMessage()
                                );
                            }

// Actualizar el árbol BST
                            arbol = new ArbolBST();

                            arbol.cargarDesdeLista(
                                    gestionPeliculas.getPeliculas()
                            );

                            System.out.println("\nPelícula registrada correctamente.");

                            System.out.print("\n¿Desea registrar otra película? (S/N): ");
                            continuar = sc.nextLine().trim();

                        } catch (IllegalArgumentException e) {

                            System.out.println("Error: " + e.getMessage());
                            continuar = "N";
                        }

                    } while (continuar.equalsIgnoreCase("S"));

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

                        int ultimoId
                                = gestionUsuarios.obtenerUltimoIdUtilizado();

                        System.out.println("\nÚltimo ID utilizado: " + ultimoId
                                + " (sugerido: " + (ultimoId + 1) + ")");

                        int id = leerEntero(sc, "ID del nuevo usuario: ");

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        Usuario nuevoUsuario
                                = new Usuario(id, nombre);

                        System.out.print(
                                "Géneros preferidos separados por coma: "
                        );

                        String entradaGeneros = sc.nextLine();

                        if (!entradaGeneros.trim().isEmpty()) {

                            String[] generos
                                    = entradaGeneros.split(",");

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
                    System.out.println("\n===== PELÍCULAS ORDENADAS POR RATING =====");

                    gestionPeliculas.mostrarPeliculasEnTabla(
                            gestionPeliculas.ordenarPorRating()
                    );

                    break;

                // =================================
                // 6. ORDENAR POR AÑO
                // =================================
                case 6:

                    System.out.println("\n===== PELÍCULAS ORDENADAS POR AÑO =====");

                    gestionPeliculas.mostrarPeliculasEnTabla(
                            gestionPeliculas.ordenarPorAnio()
                    );

                    break;

                // =================================
                // 7. BÚSQUEDA BINARIA POR CÓDIGO
                // =================================
                case 7:

                    System.out.println("\n===== BUSCAR PELÍCULA POR CÓDIGO =====");

                    int codigoBuscar = leerEntero(sc, "Ingrese el código de la película: ");

                    // Búsqueda binaria: ordena por código y luego busca
                    Pelicula encontrada = gestionPeliculas.buscarPorCodigoBinaria(codigoBuscar);

                    if (encontrada != null) {

                        System.out.println("\nPelícula encontrada:");

                        ArrayList<Pelicula> resultado = new ArrayList<>();
                        resultado.add(encontrada);

                        gestionPeliculas.mostrarPeliculasEnTabla(resultado);

                    } else {

                        System.out.println("No se encontró ninguna película con ese código.");
                    }

                    break;

                //--------------------------------------------------------------------------------------------------------------
                // =================================
                // 8. BÚSQUEDA PARCIAL POR TÍTULO
                // =================================
                case 8:

                    System.out.println("\n===== BUSCAR PELÍCULA POR TÍTULO =====");

                    System.out.print("Ingrese el título o una palabra: ");
                    String texto = sc.nextLine();

                    try {

                        // Búsqueda exacta en el árbol BST (recursiva)
                        Pelicula exacta = arbol.buscarPorTitulo(texto);

                        System.out.println("\nPelícula encontrada en el BST:");

                        ArrayList<Pelicula> resultadoExacto = new ArrayList<>();
                        resultadoExacto.add(exacta);

                        gestionPeliculas.mostrarPeliculasEnTabla(resultadoExacto);

                    } catch (PeliculaNoEncontradaException e) {

                        // Si no hay título exacto, se buscan coincidencias parciales
                        System.out.println(e.getMessage());

                        ArrayList<Pelicula> resultados
                                = arbol.buscarPorCoincidencia(texto);

                        if (resultados.isEmpty()) {

                            System.out.println("Tampoco hay títulos que contengan ese texto.");

                        } else {

                            System.out.println("\nTítulos que contienen \"" + texto.trim() + "\":");

                            gestionPeliculas.mostrarPeliculasEnTabla(resultados);
                        }

                    } catch (IllegalArgumentException e) {

                        System.out.println("Error: " + e.getMessage());

                    }

                    break;
//----------------------------------------------------------------------------------------------------------------------

                // =================================
                // 9. AGREGAR A COLA POR VER
                // =================================
                case 9:

                    int idCola
                            = leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioCola
                            = gestionUsuarios.buscarPorId(idCola);

                    if (usuarioCola == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    int codigoCola
                            = leerEntero(sc, "Código de película: ");

                    Pelicula peliculaCola
                            = gestionPeliculas.buscarPorCodigo(codigoCola);

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

                    int idPendiente
                            = leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioPendiente
                            = gestionUsuarios.buscarPorId(idPendiente);

                    if (usuarioPendiente == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    System.out.println("\n===== PELÍCULAS POR VER =====");

                    Queue<Pelicula> cola
                            = usuarioPendiente.getPorVer();

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

                    int idSiguiente
                            = leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioSiguiente
                            = gestionUsuarios.buscarPorId(idSiguiente);

                    if (usuarioSiguiente == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    Pelicula siguiente
                            = usuarioSiguiente.verSiguiente();

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

                    int idVista
                            = leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioVista
                            = gestionUsuarios.buscarPorId(idVista);

                    if (usuarioVista == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    int codigoVista
                            = leerEntero(sc, "Código de película: ");

                    Pelicula peliculaVista
                            = gestionPeliculas.buscarPorCodigo(codigoVista);

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

                    int idHistorial
                            = leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioHistorial
                            = gestionUsuarios.buscarPorId(idHistorial);

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
                        for (int i
                                = usuarioHistorial.getHistorial().size() - 1;
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

                    int idRecomendacion
                            = leerEntero(sc, "ID del usuario: ");

                    Usuario usuarioRecomendacion
                            = gestionUsuarios.buscarPorId(idRecomendacion);

                    if (usuarioRecomendacion == null) {

                        System.out.println("Usuario no encontrado.");

                        break;
                    }

                    double minimo
                            = leerDouble(sc, "Rating mínimo (1 a 5): ");

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
