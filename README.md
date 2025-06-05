Este es el proyecto java. Tiene estructura modelo, vista, controlador y una test suite de pruebas.
El programa permite crear cuentas de organizadores los cuales podran crear eventos.
Luego permite crear cuentas de usuarios que permitirán inscribirse a los eventos creados.
Todo esto se prueba en la TestSuite hecha con la librería JUnit5.


Estructura:

Programacion/
├── src/
│   ├── controller/
│   │   ├── CoLinker.java
│   │   ├── CoPrincipal.java
│   │   ├── Main.java
│   │   ├── ServicioUsuario.java
│   │   └── ServicioEvento.java
│   │
│   ├── model/
│   │   ├── Categoria.java
│   │   ├── Evento.java
│   │   ├── Inscripcion.java
│   │   ├── Organizador.java
│   │   ├── Usuario.java
│   │   └── Ubicacion.java
│   │
│   └── view/
│       ├── ViewPrincipal.java
│       ├── ViewUsuario.java
│       ├── ViewOrganizador.java
│       └── ViewEvento.java
│
├── test/
│   └── paquetePruebas/
│       ├── ServicioUsuarioTest.java
│       ├── ServicioEventoTest.java
│       ├── InscripcionTest.java
│       └── AllTestsSuite.java
│
└── README.md
