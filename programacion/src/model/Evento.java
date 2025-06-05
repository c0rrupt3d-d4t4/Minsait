package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private final int id;
    private final String nombre;
    private final LocalDateTime fechaHoraInicio;
    private final int duracionMinutos;
    private final Categoria categoria;
    private final Ubicacion ubicacion;
    private final Organizador organizador;
    private boolean cancelado = false;
    private final List<Inscripcion> inscripciones = new ArrayList<>();

    public Evento(int id,
                 String nombre,
                 LocalDateTime fechaHoraInicio,
                 int duracionMinutos,
                 Categoria categoria,
                 Ubicacion ubicacion,
                 Organizador organizador) {
        this.id = id;
        this.nombre = nombre;
        this.fechaHoraInicio = fechaHoraInicio;
        this.duracionMinutos = duracionMinutos;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.organizador = organizador;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public Categoria getCategoria() { return categoria; }
    public Ubicacion getUbicacion() { return ubicacion; }
    public Organizador getOrganizador() { return organizador; }
    public boolean isCancelado() { return cancelado; }

    /** Para compatibilidad con ViewEvento: */
    public List<Inscripcion> getListaInscripciones() {
        return inscripciones;
    }

    /** Versión estándar: */
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public boolean agregarInscripcion(Inscripcion i) {
        for (Inscripcion x : inscripciones) {
            if (x.getUsuario().getId() == i.getUsuario().getId()) {
                return false;
            }
        }
        inscripciones.add(i);
        return true;
    }

    public boolean cancelarInscripcion(Usuario u) {
        Inscripcion paraQuitar = null;
        for (Inscripcion i : inscripciones) {
            if (i.getUsuario().getId() == u.getId()) {
                paraQuitar = i;
                break;
            }
        }
        if (paraQuitar != null) {
            inscripciones.remove(paraQuitar);
            return true;
        }
        return false;
    }

    public void cancelarEvento() {
        cancelado = true;
    }

    @Override
    public String toString() {
        String estado = cancelado ? " [CANCELADO]" : "";
        return String.format(
            "Evento #%d → %s%s\n  Fecha: %s | Duración: %d min\n  Categoría: %s | Ubicación: %s\n  Organizador: %s",
            id, nombre, estado,
            fechaHoraInicio, duracionMinutos,
            categoria.getNombre(), ubicacion.toString(),
            organizador.getNombre()
        );
    }
}
