package model;

import java.util.ArrayList;
import java.util.List;

public class Organizador {
    private final int id;
    private final String nombre;
    private final String correo;
    private final String password;
    private final List<Evento> eventosCreados = new ArrayList<>();

    public Organizador(int id, String nombre, String correo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public List<Evento> getEventosCreados() { return eventosCreados; }

    public void agregarEvento(Evento e) {
        eventosCreados.add(e);
    }

    @Override
    public String toString() {
        return nombre + " <" + correo + ">";
    }
}
