package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final int id;
    private final String nombre;
    private final String correo;
    private final String password;
    private final List<Inscripcion> inscripciones = new ArrayList<>();

    public Usuario(int id, String nombre, String correo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }

    public boolean agregarInscripcion(Inscripcion i) {
        return inscripciones.add(i);
    }

    public boolean cancelarInscripcion(Evento e) {
        Inscripcion paraQuitar = inscripciones.stream()
            .filter(i -> i.getEvento().getId() == e.getId())
            .findFirst().orElse(null);
        if (paraQuitar != null) {
            inscripciones.remove(paraQuitar);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre + " <" + correo + ">";
    }
}
