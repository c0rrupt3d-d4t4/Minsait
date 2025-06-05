package view;

import java.util.List;

import model.Evento;
import model.Inscripcion;
import model.Usuario;

/**
 * Vista auxiliar para mostrar los detalles de un evento
 * y la lista de inscripciones (si se desea).
 */
public class ViewEvento {

    /**
     * Muestra la información completa de un solo evento.
     */
    public static void mostrarDetalleEvento(Evento evento) {
        System.out.println("--------------------------------------");
        System.out.println("DETALLES DEL EVENTO");
        System.out.println("--------------------------------------");
        System.out.println(evento.toString());
        System.out.println("Asistentes inscritos: " + evento.getListaInscripciones().size());
    }

    /**
     * Muestra la lista de todos los asistentes de un evento.
     */
    public static void mostrarListaAsistentes(List<Inscripcion> listaInscripciones) {
        System.out.println("----- Lista de Asistentes -----");
        if (listaInscripciones.isEmpty()) {
            System.out.println("No hay inscripciones para este evento.");
        } else {
            for (Inscripcion i : listaInscripciones) {
                Usuario u = i.getUsuario();
                System.out.println("- " + u.getNombre() + " (" + u.getCorreo() + ")");
            }
        }
    }
}
