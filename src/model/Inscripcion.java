package model;

public class Inscripcion {
    private final int id;
    private final Usuario usuario;
    private final Evento evento;

    public Inscripcion(int id, Usuario usuario, Evento evento) {
        this.id = id;
        this.usuario = usuario;
        this.evento = evento;
    }

    public int getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Evento getEvento() { return evento; }

    @Override
    public String toString() {
        return "Inscripción #" + id +
               " → Usuario: " + usuario.getNombre() +
               " | Evento: " + evento.getNombre();
    }
}
