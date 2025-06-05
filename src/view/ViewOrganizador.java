package view;

/**
 * Vista para las opciones relacionadas con los organizadores (registro, login y menú interno).
 */
public class ViewOrganizador {

    public static void mostrarMenuOrganizador() {
        System.out.println("--------------------------------------");
        System.out.println("     MENÚ DE ORGANIZADOR      ");
        System.out.println("--------------------------------------");
        System.out.println("1. Registrarse como organizador nuevo");
        System.out.println("2. Iniciar sesión");
        System.out.println("0. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
    }

    public static void mostrarMenuInternoOrganizador(String nombreOrganizador) {
        System.out.println("--------------------------------------");
        System.out.println("  Bienvenido, " + nombreOrganizador + "   ");
        System.out.println("--------------------------------------");
        System.out.println("1. Crear nuevo evento");
        System.out.println("2. Ver mis eventos creados");
        System.out.println("3. Cancelar evento");
        System.out.println("0. Cerrar sesión");
        System.out.print("Selecciona una opción: ");
    }
}
