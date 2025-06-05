package controller;

import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Evento;
import model.Inscripcion;
import model.Organizador;
import model.Usuario;
import model.Ubicacion;

public class CoLinker {

    public void iniciarAplicacion() {
        List<Usuario> usuarios = new ArrayList<>();
        List<Organizador> organizadores = new ArrayList<>();
        List<Evento> eventos = new ArrayList<>();
        List<Inscripcion> inscripciones = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<Ubicacion> ubicaciones = new ArrayList<>();

        cargarIniciales(categorias, ubicaciones);

        CoPrincipal principal = new CoPrincipal(
            usuarios,
            organizadores,
            eventos,
            inscripciones,
            categorias,
            ubicaciones
        );
        principal.ejecutar();
    }

    private void cargarIniciales(List<Categoria> cats, List<Ubicacion> ubs) {
        cats.add(new Categoria(1, "Conferencia"));
        cats.add(new Categoria(2, "Taller"));
        cats.add(new Categoria(3, "Actividad Ecológica"));
        cats.add(new Categoria(4, "Otro"));

        ubs.add(new Ubicacion(1, false, "Av. de Bruselas, 35, 28108 Alcobendas, Madrid", null));
        ubs.add(new Ubicacion(2, true, null, "https://zoom.us/j/xxxxx"));
    }
}
