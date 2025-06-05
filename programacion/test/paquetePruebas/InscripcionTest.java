package paquetePruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.ServicioEvento;
import controller.ServicioUsuario;
import model.Categoria;
import model.Evento;
import model.Inscripcion;
import model.Organizador;
import model.Usuario;
import model.Ubicacion;

@DisplayName("Pruebas de Inscripción de Usuario a Evento")
class InscripcionTest {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Organizador> organizadores = new ArrayList<>();
    private final List<Evento> eventos = new ArrayList<>();
    private final List<Inscripcion> inscripciones = new ArrayList<>();
    private final ServicioUsuario servicioUsuario = new ServicioUsuario();
    private final ServicioEvento servicioEvento = new ServicioEvento();

    @Test
    @DisplayName("Inscribir un mismo usuario dos veces al mismo evento")
    void testInscripcionDuplicada() {
        // Crear usuario y organizador
        Usuario u = servicioUsuario.registrarUsuario(usuarios, "TestUser", "test@dom.com", "pwd");
        Organizador o = new Organizador(organizadores.size() + 1, "OrgPrueba", "org@dom.com", "pwd");
        organizadores.add(o);

        // Crear categoría y ubicación manualmente para esta prueba
        Categoria cat = new Categoria(1, "Conferencia");
        Ubicacion ub = new Ubicacion(1, true, null, "https://zoom.link/xyz");

        // Crear evento válido
        Evento e = servicioEvento.crearEvento(eventos, "EventoTest", "10-10-2025", "11:45", 90, cat, ub, o);

        // Primera inscripción debe tener éxito
        Inscripcion ins1 = new Inscripcion(inscripciones.size() + 1, u, e);
        boolean agregado1 = e.agregarInscripcion(ins1);
        if (agregado1) {
            inscripciones.add(ins1);
            u.agregarInscripcion(ins1);
        }
        assertTrue(agregado1, "La primera inscripción debería ser exitosa.");
        assertEquals(1, e.getListaInscripciones().size());
        assertEquals(1, u.getInscripciones().size());

        // Segunda inscripción debe fallar
        Inscripcion ins2 = new Inscripcion(inscripciones.size() + 1, u, e);
        boolean agregado2 = e.agregarInscripcion(ins2);
        if (agregado2) {
            inscripciones.add(ins2);
            u.agregarInscripcion(ins2);
        }
        assertFalse(agregado2, "No debería permitir inscribir dos veces al mismo usuario.");
        assertEquals(1, e.getListaInscripciones().size());
        assertEquals(1, u.getInscripciones().size());
    }

    @Test
    @DisplayName("Cancelar inscripción existente y no existente")
    void testCancelarInscripcion() {
        Usuario u = servicioUsuario.registrarUsuario(usuarios, "OtroUser", "otro@dom.com", "pwd");
        Organizador o = new Organizador(organizadores.size() + 1, "Org2", "org2@dom.com", "pwd");
        organizadores.add(o);

        Categoria cat = new Categoria(2, "Taller");
        Ubicacion ub = new Ubicacion(2, false, "C/ Ejemplo 45", null);
        Evento e = servicioEvento.crearEvento(eventos, "EventoCancel", "20-11-2025", "09:00", 120, cat, ub, o);

        // Inscribir y luego cancelar
        Inscripcion ins = new Inscripcion(inscripciones.size() + 1, u, e);
        boolean agregado = e.agregarInscripcion(ins);
        if (agregado) {
            inscripciones.add(ins);
            u.agregarInscripcion(ins);
        }
        assertTrue(agregado, "La inscripción inicial debería ser exitosa.");

        boolean canceladoPorEvento = e.cancelarInscripcion(u);
        boolean canceladoPorUsuario = u.cancelarInscripcion(e);
        assertTrue(canceladoPorEvento, "El evento debería cancelar la inscripción.");
        assertTrue(canceladoPorUsuario, "El usuario debería cancelar su inscripción.");
        assertEquals(0, e.getListaInscripciones().size());
        assertEquals(0, u.getInscripciones().size());

        // Intentar cancelar de nuevo: debe devolver false
        assertFalse(e.cancelarInscripcion(u));
        assertFalse(u.cancelarInscripcion(e));
    }
}
