package controller;

import java.util.List;
import model.Usuario;

public class ServicioUsuario {

    public Usuario registrarUsuario(List<Usuario> usuarios, String nombre, String correo, String password) {
        if (correo == null || !correo.contains("@")) {
            return null;
        }
        Usuario u = new Usuario(usuarios.size() + 1, nombre, correo, password);
        usuarios.add(u);
        return u;
    }

    public Usuario loginUsuario(List<Usuario> usuarios, String correo, String password) {
        return usuarios.stream()
            .filter(u -> u.getCorreo().equals(correo) && u.getPassword().equals(password))
            .findFirst()
            .orElse(null);
    }
}
