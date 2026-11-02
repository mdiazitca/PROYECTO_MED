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

public class GestionPeliculas {

    private ArrayList<Pelicula> peliculas;

    // Constructor
    public GestionPeliculas() {
        peliculas = new ArrayList<>();
    }

    // Agregar película
    public void agregarPelicula(Pelicula pelicula) {

        if (pelicula == null) {
            throw new IllegalArgumentException(
                "La película no puede ser nula."
            );
        }

        if (buscarPorCodigo(pelicula.getCodigo()) != null) {
            throw new IllegalArgumentException(
                "Ya existe una película con ese código."
            );
        }

        peliculas.add(pelicula);
    }

    // Mostrar películas
    public void mostrarPeliculas() {

        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas.");
            return;
        }

        for (Pelicula p : peliculas) {
            System.out.println(p);
        }
    }

    // Buscar película por código
    public Pelicula buscarPorCodigo(int codigo) {

        for (Pelicula p : peliculas) {

            if (p.getCodigo() == codigo) {
                return p;
            }
        }

        return null;
    }

    // Obtener lista de películas
    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }
   
    // =====================================
    // BUBBLE SORT: ORDENAR POR RATING
    // De mayor a menor
    // =====================================

    public ArrayList<Pelicula> ordenarPorRating() {

        ArrayList<Pelicula> lista = new ArrayList<>(peliculas);

        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - 1 - i; j++) {

                if (lista.get(j).getRating()
                        < lista.get(j + 1).getRating()) {

                    Pelicula temporal = lista.get(j);

                    lista.set(j, lista.get(j + 1));

                    lista.set(j + 1, temporal);
                }
            }
        }

        return lista;
    }


    // =====================================
    // SELECTION SORT: ORDENAR POR AÑO
    // De menor a mayor
    // =====================================

    public ArrayList<Pelicula> ordenarPorAnio() {

        ArrayList<Pelicula> lista = new ArrayList<>(peliculas);

        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {

            int menor = i;

            for (int j = i + 1; j < n; j++) {

                if (lista.get(j).getAnio()
                        < lista.get(menor).getAnio()) {

                    menor = j;
                }
            }

            Pelicula temporal = lista.get(i);

            lista.set(i, lista.get(menor));

            lista.set(menor, temporal);
        }

        return lista;
    }

    // =====================================
    // BÚSQUEDA BINARIA POR CÓDIGO
    // =====================================

    public Pelicula buscarPorCodigoBinaria(int codigo) {

        // Crear una copia de las películas
        ArrayList<Pelicula> lista = new ArrayList<>(peliculas);

        // Ordenar la copia por código de menor a mayor
        lista.sort((a, b) ->
            Integer.compare(a.getCodigo(), b.getCodigo())
        );

        int inicio = 0;
        int fin = lista.size() - 1;

        // Realizar búsqueda binaria
        while (inicio <= fin) {

            int medio = (inicio + fin) / 2;

            int codigoMedio = lista.get(medio).getCodigo();

            if (codigoMedio == codigo) {
                return lista.get(medio);
            }

            if (codigo < codigoMedio) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }

        return null;
    }
 
}

