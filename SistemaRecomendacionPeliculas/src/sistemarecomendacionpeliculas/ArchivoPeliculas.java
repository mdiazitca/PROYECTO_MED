/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarecomendacionpeliculas;

/**
 *
 * @author liliq
 */



import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ArchivoPeliculas {

    // =====================================
    // GUARDAR PELÍCULAS EN UN ARCHIVO
    // =====================================

    public void guardarPeliculas(
            ArrayList<Pelicula> peliculas, String nombreArchivo)
            throws IOException {

        Path ruta = Paths.get(nombreArchivo);

        try (BufferedWriter escritor = Files.newBufferedWriter(
                ruta, StandardCharsets.UTF_8)) {

            for (Pelicula pelicula : peliculas) {

                String titulo = Base64.getEncoder().encodeToString(
                        pelicula.getTitulo().getBytes(StandardCharsets.UTF_8));

                String genero = Base64.getEncoder().encodeToString(
                        pelicula.getGenero().getBytes(StandardCharsets.UTF_8));

                escritor.write(
                        pelicula.getCodigo() + "\t"
                        + titulo + "\t"
                        + genero + "\t"
                        + pelicula.getAnio() + "\t"
                        + pelicula.getRating()
                );

                escritor.newLine();
            }
        }
    }

    // =====================================
    // CARGAR PELÍCULAS DESDE EL ARCHIVO
    // =====================================

    public ArrayList<Pelicula> cargarPeliculas(String nombreArchivo)
            throws IOException {

        ArrayList<Pelicula> peliculas = new ArrayList<>();

        Path ruta = Paths.get(nombreArchivo);

        if (!Files.exists(ruta)) {
            return peliculas;
        }

        try (BufferedReader lector = Files.newBufferedReader(
                ruta, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split("\t", -1);

                if (datos.length != 5) {
                    throw new IOException("Formato incorrecto en el archivo.");
                }

                try {
                    int codigo = Integer.parseInt(datos[0]);

                    String titulo = new String(
                            Base64.getDecoder().decode(datos[1]),
                            StandardCharsets.UTF_8);

                    String genero = new String(
                            Base64.getDecoder().decode(datos[2]),
                            StandardCharsets.UTF_8);

                    int anio = Integer.parseInt(datos[3]);
                    double rating = Double.parseDouble(datos[4]);

                    Pelicula pelicula = new Pelicula(
                            codigo, titulo, genero, anio, rating);

                    peliculas.add(pelicula);

                } catch (IllegalArgumentException e) {
                    throw new IOException(
                            "Hay datos inválidos en el archivo.", e);
                }
            }
        }

        return peliculas;
    }
}

