// javascript/detalle.js

document.addEventListener("DOMContentLoaded", () => {
  // 1) Mismo array de eventos, pero con "descripcionCompleta", "ubicacion" y "organizadores".
  const eventos = [
    {
      id: 1,
      titulo: "Concierto de Jazz en la Plaza Mayor",
      fecha: "2025-07-15",
      descripcionCompleta: `
        <p>Disfruta de los mejores músicos de jazz nacionales e internacionales en la Plaza Mayor.
        Habrá concesiones gastronómicas, barra libre y actividades paralelas.</p>
        <ul>
          <li><strong>Ubicación:</strong> Plaza Mayor, Ciudad.</li>
          <li><strong>Organizadores:</strong> Fundación Música Urbana.</li>
          <li><strong>Precio:</strong> Entrada gratuita (aforo limitado).</li>
        </ul>
      `,
      rutaImagen: "../images/Concierto.jpg"
    },
    {
      id: 2,
      titulo: "Feria Gourmet de Verano",
      fecha: "2025-08-02",
      descripcionCompleta: `
        <p>Ven a la Feria Gourmet de Verano y prueba platos de chefs reconocidos.
        Talleres en vivo, showcooking y música en directo.</p>
        <ul>
          <li><strong>Ubicación:</strong> Recinto Ferial, Av. de los Sabores.</li>
          <li><strong>Organizadores:</strong> Asociación Gastronómica Nacional.</li>
          <li><strong>Precio:</strong> 5€ (entrada general), talleres con coste adicional.</li>
        </ul>
      `,
      rutaImagen: "../images/Feria.jpg"
    },
    {
      id: 3,
      titulo: "Maratón Solidario",
      fecha: "2025-09-10",
      descripcionCompleta: `
        <p>Únete al Maratón Solidario y apoya proyectos de ayuda a personas vulnerables.
        Recorrido urbano de 10 km por el casco histórico.</p>
        <ul>
          <li><strong>Ubicación:</strong> Inicio en Parque Central.</li>
          <li><strong>Organizadores:</strong> Fundación Corredores Solidarios.</li>
          <li><strong>Inscripción:</strong> 15€ (incluye dorsal y camiseta técnica).</li>
        </ul>
      `,
      rutaImagen: "../images/maraton.webp"
    },
    {
      id: 4,
      titulo: "Exposición de Fotografía Contemporánea",
      fecha: "2025-10-05",
      descripcionCompleta: `
        <p>Galería abierta con más de 50 fotografías de autores emergentes del país.
        Charlas y visitas guiadas los fines de semana.</p>
        <ul>
          <li><strong>Ubicación:</strong> Museo de Arte Moderno.</li>
          <li><strong>Organizadores:</strong> Academia de Bellas Artes.</li>
          <li><strong>Horario:</strong> De 10:00 a 20:00 (martes cerrado).</li>
        </ul>
      `,
      rutaImagen: "../images/foto.webp"
    },
    {
      id: 5,
      titulo: "Festival de Teatro al Aire Libre",
      fecha: "2025-11-20",
      descripcionCompleta: `
        <p>Disfruta de teatro contemporáneo y clásico en el anfiteatro del parque.
        Sesiones al atardecer con entrada por orden de llegada.</p>
        <ul>
          <li><strong>Ubicación:</strong> Anfiteatro del Parque del Lago.</li>
          <li><strong>Organizadores:</strong> Compañía Teatral Horizonte.</li>
          <li><strong>Precio:</strong> Entrada libre, donativos voluntarios.</li>
        </ul>
      `,
      rutaImagen: "../images/teatro.jpg"
    }
  ];

  // 2) Función para formatear fecha
  function formateaFecha(fechaISO) {
    const meses = [
      "enero","febrero","marzo","abril","mayo","junio",
      "julio","agosto","septiembre","octubre","noviembre","diciembre"
    ];
    const [yy, mm, dd] = fechaISO.split("-");
    const dia = parseInt(dd, 10);
    const mes = meses[parseInt(mm, 10) - 1];
    return `${dia} de ${mes} de ${yy}`;
  }

  // 3) Obtener el parámetro "id" de la URL
  function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
  }

  const contenedor = document.getElementById("detalle-evento");
  const idEv = getQueryParam("id");
  const evento = eventos.find(e => e.id === parseInt(idEv, 10));

  if (!evento) {
    contenedor.innerHTML = `
      <h2>Evento no encontrado</h2>
      <p>Lo sentimos, el evento que buscas no existe o ha sido eliminado.</p>
      <a href="eventos.html" class="btn-volver">Volver al listado de eventos</a>
    `;
    return;
  }

  // 4) Si existe, muéstralo completo
  contenedor.innerHTML = `
    <article class="evento-detalle">
      <h1>${evento.titulo}</h1>
      <p class="fecha-evento">${formateaFecha(evento.fecha)}</p>
      <div class="imagen-detalle">
        <img src="${evento.rutaImagen}" alt="${evento.titulo}" />
      </div>
      <div class="info-detalle">
        ${evento.descripcionCompleta}
      </div>
      <div class="link-volver">
        <a href="eventos.html">← Volver al listado</a>
      </div>
    </article>
  `;
});
