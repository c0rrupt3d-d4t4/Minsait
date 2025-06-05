package view;

/**
 * Vista para las opciones relacionadas con los usuarios (registro, login y menú interno).
 */
public class ViewUsuario {

    public static void mostrarMenuUsuario() {
        System.out.println("--------------------------------------");
        System.out.println("       MENÚ DE USUARIO       ");
        System.out.println("--------------------------------------");
        System.out.println("1. Registrarse como usuario nuevo");
        System.out.println("2. Iniciar sesión");
        System.out.println("0. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
    }

    public static void mostrarMenuInternoUsuario(String nombreUsuario) {
        System.out.println("--------------------------------------");
        System.out.println("   Bienvenido, " + nombreUsuario + "   ");
        System.out.println("--------------------------------------");
        System.out.println("1. Ver lista de eventos y suscribirme");
        System.out.println("2. Ver mis inscripciones");
        System.out.println("3. Cancelar inscripción");
        System.out.println("0. Cerrar sesión");
        System.out.print("Selecciona una opción: ");
    }
}
