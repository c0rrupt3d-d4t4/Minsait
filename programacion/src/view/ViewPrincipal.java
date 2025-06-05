package view;

/**
 * Vista principal: muestra el menú inicial donde el usuario elige si entra como Usuario, Organizador o sale.
 */
public class ViewPrincipal {

    public static void mostrarMenuPrincipal() {
        System.out.println("--------------------------------------");
        System.out.println("   PORTAL DE GESTIÓN DE EVENTOS SVT   ");
        System.out.println("--------------------------------------");
        System.out.println("1. Entrar como USUARIO");
        System.out.println("2. Entrar como ORGANIZADOR");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }
}
