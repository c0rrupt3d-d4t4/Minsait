package paquetePruebas;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    ServicioUsuarioTest.class,
    ServicioEventoTest.class,
    InscripcionTest.class
})
public class AllTestsSuite {

}
