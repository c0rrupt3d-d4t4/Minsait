package paquetePruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.ServicioUsuario;
import model.Usuario;

@DisplayName("Pruebas de ServicioUsuario")
class ServicioUsuarioTest {

    private final ServicioUsuario servicioUsuario = new ServicioUsuario();
    private final List<Usuario> listaUsuarios = new ArrayList<>();

    @ParameterizedTest(name = "Correo \"{1}\" → válido={3}")
    @CsvSource({
        // nombre, correo, contraseña, resultado esperado (true=se crea, false=null)
        "Ana López, ana@example.com, pass123, true",
        "Juan Pérez, juan.ejemplo.com, 1234, false",
        "María Giménez, mari@gimenez, pwd, true",
        "Pedro, pedro@dominio, abc, true",
        "Error User, sinArroba, key, false"
    })
    @DisplayName("registroUsuario(): distintos correos")
    void testRegistrarUsuario_conDistintosCorreos(String nombre, String correo, String password, boolean valido) {
        Usuario u = servicioUsuario.registrarUsuario(listaUsuarios, nombre, correo, password);
        if (valido) {
            assertNotNull(u, () -> "Se esperaba que el usuario “" + nombre + " / " + correo + "” se creara correctamente.");
            assertEquals(nombre, u.getNombre());
            assertEquals(correo, u.getCorreo());
            assertTrue(listaUsuarios.contains(u));
        } else {
            assertNull(u, () -> "Se esperaba que no se creara un usuario con correo inválido: " + correo);
        }
    }

    @Test
    @DisplayName("loginUsuario(): usuario existente y contraseña correcta")
    void testLoginUsuario_success() {
        // Precondición: damos de alta un usuario
        Usuario creado = servicioUsuario.registrarUsuario(listaUsuarios, "Laura", "laura@dominio.com", "clave");
        assertNotNull(creado);

        Usuario resultado = servicioUsuario.loginUsuario(listaUsuarios, "laura@dominio.com", "clave");
        assertNotNull(resultado);
        assertEquals(creado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("loginUsuario(): fallos por correo o contraseña incorrecta")
    void testLoginUsuario_failure() {
        servicioUsuario.registrarUsuario(listaUsuarios, "Luis", "luis@dominio.com", "pass");
        // Contraseña incorrecta
        Usuario r1 = servicioUsuario.loginUsuario(listaUsuarios, "luis@dominio.com", "otra");
        assertNull(r1);
        // Correo inexistente
        Usuario r2 = servicioUsuario.loginUsuario(listaUsuarios, "noexiste@dominio.com", "pass");
        assertNull(r2);
    }
}
