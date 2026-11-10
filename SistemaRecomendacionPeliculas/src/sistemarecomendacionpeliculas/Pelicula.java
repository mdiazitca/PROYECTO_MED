package sistemarecomendacionpeliculas;
public class Pelicula {

    private int codigo;
    private String titulo;
    private String genero;
    private int anio;
    private double rating; // rango válido: 1 a 5

    public Pelicula(int codigo, String titulo, String genero, int anio, double rating) {
        setCodigo(codigo);
        setTitulo(titulo);
        setGenero(genero);
        setAnio(anio);
        setRating(rating);
    }

    // ---------- Getters ----------
    public int getCodigo()    { return codigo; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public int getAnio()      { return anio; }
    public double getRating() { return rating; }

    // ---------- Setters con validación ----------
    public void setCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser un número positivo.");
        }
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.titulo = titulo.trim();
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("El género no puede estar vacío.");
        }
        this.genero = genero.trim();
    }

    public void setAnio(int anio) {
        if (anio < 1888 || anio > 2100) { // 1888: primera película registrada
            throw new IllegalArgumentException("Año de estreno inválido: " + anio);
        }
        this.anio = anio;
    }

  public void setRating(double rating) {

    if (!Double.isFinite(rating) || rating < 1 || rating > 5) {

        throw new IllegalArgumentException(
            "El rating debe ser un número entre 1 y 5."
        );
    }

    this.rating = rating;
}

    @Override
    public String toString() {
        return String.format("[%d] %s (%d) - %s - Rating: %.1f",
                codigo, titulo, anio, genero, rating);
    }
}

