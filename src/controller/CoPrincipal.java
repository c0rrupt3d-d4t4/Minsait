package controller;

import java.util.List;
import java.util.Scanner;

import model.Categoria;
import model.Evento;
import model.Inscripcion;
import model.Organizador;
import model.Usuario;
import model.Ubicacion;
import view.ViewOrganizador;
import view.ViewPrincipal;
import view.ViewUsuario;

public class CoPrincipal {
    private final List<Usuario> usuarios;
    private final List<Organizador> organizadores;
    private final List<Evento> eventos;
    private final List<Inscripcion> inscripciones;
    private final List<Categoria> categorias;
    private final List<Ubicacion> ubicaciones;

    private final ServicioUsuario servicioUsuario = new ServicioUsuario();
    private final ServicioEvento servicioEvento = new ServicioEvento();
    private final Scanner scanner = new Scanner(System.in);

    public CoPrincipal(
        List<Usuario> usuarios,
        List<Organizador> organizadores,
        List<Evento> eventos,
        List<Inscripcion> inscripciones,
        List<Categoria> categorias,
        List<Ubicacion> ubicaciones
    ) {
        this.usuarios = usuarios;
        this.organizadores = organizadores;
        this.eventos = eventos;
        this.inscripciones = inscripciones;
        this.categorias = categorias;
        this.ubicaciones = ubicaciones;
    }

    public void ejecutar() {
        while (true) {
            ViewPrincipal.mostrarMenuPrincipal();
            int opcion = pedirEntero("Opción: ");
            switch (opcion) {
                case 1:
                    manejarUsuario();
                    break;
                case 2:
                    manejarOrganizador();
                    break;
                case 0:
                    System.out.println("Cerrando programa.");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    /*================ USUARIO ================*/

    private void manejarUsuario() {
        while (true) {
            ViewUsuario.mostrarMenuUsuario();
            int op = pedirEntero("Opción: ");
            if (op == 1) {
                registrarUsuario();
            } else if (op == 2) {
                Usuario u = loginUsuario();
                if (u != null) menuUsuarioLogueado(u);
            } else if (op == 0) {
                return;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private void registrarUsuario() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo (@): ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();

        Usuario nuevo = servicioUsuario.registrarUsuario(usuarios, nombre, correo, pass);
        if (nuevo == null) {
            System.out.println("Correo inválido. Debe contener '@'.");
        } else {
            System.out.println("Usuario creado (ID=" + nuevo.getId() + ").");
        }
    }

    private Usuario loginUsuario() {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();

        Usuario u = servicioUsuario.loginUsuario(usuarios, correo, pass);
        if (u == null) {
            System.out.println("Credenciales incorrectas.");
        }
        return u;
    }

    private void menuUsuarioLogueado(Usuario u) {
        while (true) {
            ViewUsuario.mostrarMenuInternoUsuario(u.getNombre());
            int op = pedirEntero("Opción: ");
            if (op == 1) {
                inscribirseEnEvento(u);
            } else if (op == 2) {
                verInscripciones(u);
            } else if (op == 3) {
                cancelarInscripcionUsuario(u);
            } else if (op == 0) {
                return;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private void inscribirseEnEvento(Usuario u) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos.");
            return;
        }
        System.out.println("=== Eventos Disponibles ===");
        for (Evento e : eventos) {
            if (!e.isCancelado()) {
                System.out.println(e);
            }
        }
        int idE = pedirEntero("ID evento (0 para salir): ");
        if (idE == 0) return;

        Evento eSel = null;
        for (Evento e : eventos) {
            if (e.getId() == idE) {
                eSel = e;
                break;
            }
        }
        if (eSel == null) {
            System.out.println("Evento no encontrado.");
            return;
        }
        if (eSel.isCancelado()) {
            System.out.println("Evento cancelado.");
            return;
        }

        Inscripcion insc = new Inscripcion(inscripciones.size() + 1, u, eSel);
        boolean ok = eSel.agregarInscripcion(insc);
        if (ok) {
            u.agregarInscripcion(insc);
            inscripciones.add(insc);
            System.out.println("Inscripción exitosa en \"" + eSel.getNombre() + "\".");
        } else {
            System.out.println("Ya estás inscrito.");
        }
    }

    private void verInscripciones(Usuario u) {
        if (u.getInscripciones().isEmpty()) {
            System.out.println("No tienes inscripciones.");
            return;
        }
        System.out.println("=== Mis Inscripciones ===");
        for (Inscripcion i : u.getInscripciones()) {
            System.out.println(i);
        }
    }

    private void cancelarInscripcionUsuario(Usuario u) {
        int idE = pedirEntero("ID evento a cancelar (0 para salir): ");
        if (idE == 0) return;

        Evento eSel = null;
        for (Evento e : eventos) {
            if (e.getId() == idE) {
                eSel = e;
                break;
            }
        }
        if (eSel == null) {
            System.out.println("Evento no encontrado.");
            return;
        }

        boolean uOk = u.cancelarInscripcion(eSel);
        boolean eOk = eSel.cancelarInscripcion(u);
        if (uOk && eOk) {
            Inscripcion aEliminar = null;
            for (Inscripcion ins : inscripciones) {
                if (ins.getEvento().getId() == idE 
                 && ins.getUsuario().getId() == u.getId()) {
                    aEliminar = ins;
                    break;
                }
            }
            if (aEliminar != null) {
                inscripciones.remove(aEliminar);
            }
            System.out.println("Inscripción cancelada.");
        } else {
            System.out.println("No estabas inscrito en ese evento.");
        }
    }

    /*============== ORGANIZADOR ==============*/

    private void manejarOrganizador() {
        while (true) {
            ViewOrganizador.mostrarMenuOrganizador();
            int op = pedirEntero("Opción: ");
            if (op == 1) {
                registrarOrganizador();
            } else if (op == 2) {
                Organizador o = loginOrganizador();
                if (o != null) menuOrganizadorLogueado(o);
            } else if (op == 0) {
                return;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private void registrarOrganizador() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();

        Organizador o = new Organizador(organizadores.size() + 1, nombre, correo, pass);
        organizadores.add(o);
        System.out.println("Organizador creado (ID=" + o.getId() + ").");
    }

    private Organizador loginOrganizador() {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String pass = scanner.nextLine();

        for (Organizador o : organizadores) {
            if (o.getCorreo().equals(correo) && o.getPassword().equals(pass)) {
                return o;
            }
        }
        System.out.println("Credenciales incorrectas.");
        return null;
    }

    private void menuOrganizadorLogueado(Organizador o) {
        while (true) {
            ViewOrganizador.mostrarMenuInternoOrganizador(o.getNombre());
            int op = pedirEntero("Opción: ");
            if (op == 1) {
                crearEvento(o);
            } else if (op == 2) {
                listarEventosCreados(o);
            } else if (op == 3) {
                cancelarEvento(o);
            } else if (op == 0) {
                return;
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private void crearEvento(Organizador o) {
        System.out.print("Nombre del evento: ");
        String nombre = scanner.nextLine();

        Categoria cat = seleccionarCategoria();
        if (cat == null) return;

        System.out.print("Fecha (DD-MM-YYYY): ");
        String fecha = scanner.nextLine();
        System.out.print("Hora (HH:MM): ");
        String hora = scanner.nextLine();
        int duracion = pedirEntero("Duración (min): ");

        Ubicacion ub = seleccionarUbicacion();
        if (ub == null) return;

        try {
            Evento ev = servicioEvento.crearEvento(
                eventos, nombre, fecha, hora, duracion, cat, ub, o
            );
            System.out.println("Evento creado ↪ ID=" + ev.getId());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void listarEventosCreados(Organizador o) {
        if (o.getEventosCreados().isEmpty()) {
            System.out.println("No has creado eventos.");
            return;
        }
        System.out.println("=== Tus Eventos ===");
        for (Evento e : o.getEventosCreados()) {
            System.out.println(e);
        }
    }

    private void cancelarEvento(Organizador o) {
        int idE = pedirEntero("ID evento a cancelar (0 salir): ");
        if (idE == 0) return;

        Evento ev = null;
        for (Evento e : o.getEventosCreados()) {
            if (e.getId() == idE) {
                ev = e;
                break;
            }
        }
        if (ev == null) {
            System.out.println("No es tu evento o no existe.");
            return;
        }
        ev.cancelarEvento();
        System.out.println("Evento \"" + ev.getNombre() + "\" cancelado.");
    }

    /*============= AUXILIARES =============*/

    private int pedirEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private Categoria seleccionarCategoria() {
        System.out.println("Seleccione categoría:");
        for (Categoria c : categorias) {
            System.out.println(c.getId() + ". " + c.getNombre());
        }
        int id = pedirEntero("ID categoría (0 cancelar): ");
        if (id == 0) return null;
        for (Categoria c : categorias) {
            if (c.getId() == id) {
                return c;
            }
        }
        System.out.println("Categoría inválida.");
        return null;
    }

    private Ubicacion seleccionarUbicacion() {
        System.out.println("Seleccione ubicación:");
        for (Ubicacion u : ubicaciones) {
            System.out.println(u.getId() + ". " + u);
        }
        int id = pedirEntero("ID ubicación (0 cancelar): ");
        if (id == 0) return null;
        for (Ubicacion u : ubicaciones) {
            if (u.getId() == id) {
                return u;
            }
        }
        System.out.println("Ubicación inválida.");
        return null;
    }
}
