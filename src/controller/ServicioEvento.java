package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import model.Categoria;
import model.Evento;
import model.Organizador;
import model.Ubicacion;

public class ServicioEvento {

    public Evento crearEvento(
            List<Evento> eventos,
            String nombre,
            String fechaDDMMYYYY,
            String horaHHMM,
            int duracion,
            Categoria categoria,
            Ubicacion ubicacion,
            Organizador organizador
    ) {
        // Validar y parsear fecha (DD-MM-YYYY)
        String[] f = fechaDDMMYYYY.split("-");
        if (f.length != 3) {
            throw new IllegalArgumentException("Formato de fecha inválido. Debe ser DD-MM-YYYY.");
        }
        int d, m, a;
        try {
            d = Integer.parseInt(f[0]);
            m = Integer.parseInt(f[1]);
            a = Integer.parseInt(f[2]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Día, mes y año deben ser números.");
        }
        LocalDate fecha;
        try {
            fecha = LocalDate.of(a, m, d);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Fecha inválida: " + ex.getMessage());
        }

        // Validar y parsear hora (HH:MM)
        String[] h = horaHHMM.split(":");
        if (h.length != 2) {
            throw new IllegalArgumentException("Formato de hora inválido. Debe ser HH:MM.");
        }
        int hh, mm;
        try {
            hh = Integer.parseInt(h[0]);
            mm = Integer.parseInt(h[1]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Hora y minuto deben ser números.");
        }
        LocalTime hora;
        try {
            hora = LocalTime.of(hh, mm);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Hora inválida: " + ex.getMessage());
        }

        LocalDateTime inicio = LocalDateTime.of(fecha, hora);
        Evento ev = new Evento(
            eventos.size() + 1,
            nombre,
            inicio,
            duracion,
            categoria,
            ubicacion,
            organizador
        );
        eventos.add(ev);
        organizador.agregarEvento(ev);
        return ev;
    }
}
