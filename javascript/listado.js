document.addEventListener("DOMContentLoaded", () => {
  // 1) Define aquí tu array de eventos (igual que tenías en app.js, pero de forma local):
  const eventos = [
    {
      id: 1,
      titulo: "Concierto de Jazz en la Plaza Mayor",
      fecha: "2025-07-15",
      descripcionCorta: "Una noche mágica de jazz bajo las estrellas.",
      rutaImagen: "../images/Concierto.jpg"
    },
    {
      id: 2,
      titulo: "Feria Gourmet de Verano",
      fecha: "2025-08-02",
      descripcionCorta: "Degustación de los mejores productos del país.",
      rutaImagen: "../images/Feria.jpg"
    },
    {
      id: 3,
      titulo: "Maratón Solidario",
      fecha: "2025-09-10",
      descripcionCorta: "Corre por una buena causa y colabora con ONGs locales.",
      rutaImagen: "../images/maraton.webp"
    },
    {
      id: 4,
      titulo: "Exposición de Fotografía Contemporánea",
      fecha: "2025-10-05",
      descripcionCorta: "Muestra de fotógrafos emergentes y consolidados.",
      rutaImagen: "../images/foto.webp"
    },
    {
      id: 5,
      titulo: "Festival de Teatro al Aire Libre",
      fecha: "2025-11-20",
      descripcionCorta: "Obras de teatro, monólogos y música en un entorno natural.",
      rutaImagen: "../images/teatro.jpg"
    }
  ];

  // 2) Función para formatear fecha "YYYY-MM-DD" → "D de mes de YYYY":
  function formateaFecha(fechaISO) {
    const meses = [
      "enero", "febrero", "marzo", "abril", "mayo", "junio",
      "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
    ];
    const [yy, mm, dd] = fechaISO.split("-");
    const dia = parseInt(dd, 10);
    const mes = meses[parseInt(mm, 10) - 1];
    return `${dia} de ${mes} de ${yy}`;
  }

  // 3) Construir el listado
  const contenedor = document.getElementById("listado-eventos");
  if (!contenedor) {
    console.error("No se encontró el contenedor #listado-eventos en el HTML.");
    return;
  }

  eventos.forEach(ev => {
    // Crear cada artículo de evento
    const articulo = document.createElement("article");
    articulo.classList.add("evento-item");

    articulo.innerHTML = `
      <div class="imagen-evento">
        <img src="${ev.rutaImagen}" alt="${ev.titulo}" />
      </div>
      <div class="datos-evento">
        <h2>${ev.titulo}</h2>
        <p class="fecha-evento">${formateaFecha(ev.fecha)}</p>
        <p class="descripcion-corta">${ev.descripcionCorta}</p>
        <!-- El enlace a detalle debería apuntar a detalle_evento.html?id={id} -->
        <a href="detalle_evento.html?id=${ev.id}" class="link-detalle">
          Más información
        </a>
      </div>
    `;
    contenedor.appendChild(articulo);
  });
});
