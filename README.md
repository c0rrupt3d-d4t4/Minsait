Este es el proyecto java. Tiene estructura modelo, vista, controlador y una test suite de pruebas.<br>
El programa permite crear cuentas de organizadores los cuales podran crear eventos.<br>
Luego permite crear cuentas de usuarios que permitirán inscribirse a los eventos creados.<br>
Todo esto se puede probar en la TestSuite hecha con la librería JUnit5.<br>


Estructura:<br>

Programacion/<br>
├── src/<br>
│   ├── controller/<br>
│   │   ├── CoLinker.java<br>
│   │   ├── CoPrincipal.java<br>
│   │   ├── Main.java<br>
│   │   ├── ServicioUsuario.java<br>
│   │   └── ServicioEvento.java<br>
│   │<br>
│   ├── model/<br>
│   │   ├── Categoria.java<br>
│   │   ├── Evento.java<br>
│   │   ├── Inscripcion.java<br>
│   │   ├── Organizador.java<br>
│   │   ├── Usuario.java<br>
│   │   └── Ubicacion.java<br>
│   │<br>
│   └── view/<br>
│       ├── ViewPrincipal.java<br>
│       ├── ViewUsuario.java<br>
│       ├── ViewOrganizador.java<br>
│       └── ViewEvento.java<br>
│<br>
├── test/<br>
│   └── paquetePruebas/<br>
│       ├── ServicioUsuarioTest.java<br>
│       ├── ServicioEventoTest.java<br>
│       ├── InscripcionTest.java<br>
│       └── AllTestsSuite.java<br>
│<br>
└── README.md<br>
