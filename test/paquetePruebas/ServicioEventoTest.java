package paquetePruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.ServicioEvento;
import model.Categoria;
import model.Evento;
import model.Organizador;
import model.Ubicacion;

@DisplayName("Pruebas de ServicioEvento")
class ServicioEventoTest {

    private final ServicioEvento servicioEvento = new ServicioEvento();
    private final List<Evento> listaEventos = new ArrayList<>();
    private final Organizador organizador = new Organizador(1, "Org1", "org1@dom.com", "pwd");
    private final List<Categoria> listaCategorias = Arrays.asList(
        new Categoria(1, "Conferencia"),
        new Categoria(2, "Taller")
    );
    private final List<Ubicacion> listaUbicaciones = Arrays.asList(
        new Ubicacion(1, false, "Calle Falsa 123", null),
        new Ubicacion(2, true, null, "https://zoom.link/abc")
    );

    @ParameterizedTest(name = "fecha=\"{1}\", hora=\"{2}\" → excepción={3}")
    @CsvSource({
        "Evento A, 05-06-2025, 14:30, false",
        "Evento B, 31-02-2025, 10:00, true",   // 31 de febrero inválido
        "Evento C, 29-02-2024, 09:00, false", // 2024 es bisiesto → válido
        "Evento D, 29-02-2025, 09:00, true",  // 2025 no bisiesto → inválido
        "Evento E, 15-13-2025, 12:00, true",  // mes 13 inválido
        "Evento F, 01-12-2025, 24:00, true"   // hora 24:00 inválida
    })
    @DisplayName("crearEvento(): validación de fecha y hora")
    void testCrearEvento_formatoFechaHora(String nombreEvento, String fecha, String hora, boolean lanza) {
        Categoria cat = listaCategorias.get(0);     // siempre usar categoría válida
        Ubicacion ub = listaUbicaciones.get(0);     // siempre usar ubicación válida

        if (lanza) {
            assertThrows(
                IllegalArgumentException.class,
                () -> servicioEvento.crearEvento(
                    listaEventos, nombreEvento, fecha, hora, 60, cat, ub, organizador
                ),
                () -> "Se esperaba IllegalArgumentException para fecha=" + fecha + ", hora=" + hora
            );
        } else {
            Evento e = servicioEvento.crearEvento(
                listaEventos, nombreEvento, fecha, hora, 60, cat, ub, organizador
            );
            assertNotNull(e);
            assertEquals(nombreEvento, e.getNombre());
            // Comprobamos que el LocalDateTime coincide con el parseo esperado
            String[] f = fecha.split("-");
            int dia = Integer.parseInt(f[0]), mes = Integer.parseInt(f[1]), año = Integer.parseInt(f[2]);
            String[] h = hora.split(":");
            int hh = Integer.parseInt(h[0]), mm = Integer.parseInt(h[1]);
            LocalDateTime esperada = LocalDateTime.of(año, mes, dia, hh, mm);
            assertEquals(esperada, e.getFechaHoraInicio());
            assertEquals(cat, e.getCategoria());
            assertEquals(ub, e.getUbicacion());
            assertEquals(organizador.getId(), e.getOrganizador().getId());
        }
    }
}
