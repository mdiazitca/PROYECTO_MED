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

    public class GestionUsuarios {

        private ArrayList<Usuario> usuarios;

        // Constructor
        public GestionUsuarios() {
            usuarios = new ArrayList<>();
        }

        // Agregar usuario
        public void agregarUsuario(Usuario usuario) {

            if (usuario == null) {
                throw new IllegalArgumentException(
                    "El usuario no puede ser nulo."
                );
            }

            if (buscarPorId(usuario.getId()) != null) {
                throw new IllegalArgumentException(
                    "Ya existe un usuario con ese ID."
                );
            }

            usuarios.add(usuario);
        }

        // Mostrar usuarios
        public void mostrarUsuarios() {

            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }

            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }

        // Buscar usuario por ID
        public Usuario buscarPorId(int id) {

            for (Usuario u : usuarios) {

                if (u.getId() == id) {
                    return u;
                }
            }

            return null;
        }

        // Obtener el último ID utilizado
        public int obtenerUltimoIdUtilizado() {

            int ultimoId = 0;

            for (Usuario u : usuarios) {

                if (u.getId() > ultimoId) {
                    ultimoId = u.getId();
                }
            }

            return ultimoId;
        }

        // Obtener lista de usuarios
        public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
        }
    }

