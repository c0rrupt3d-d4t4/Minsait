// javascript/carousel.js

document.addEventListener("DOMContentLoaded", () => {
  // 1) Lista de rutas relativas a las imágenes que quieres mostrar en el carrusel.
  //    Asegúrate de que estos nombres coincidan exactamente con los que tienes en /images/
  const imagenesCarousel = [
    "../images/Concierto.jpg",
    "../images/Feria.jpg",
    "../images/foto.webp",
    "../images/maraton.webp",
    "../images/teatro.jpg"
  ];

  // 2) Referencias al DOM
  const contenedor = document.getElementById("carousel-slides");
  const btnPrev = document.getElementById("carousel-prev");
  const btnNext = document.getElementById("carousel-next");

  if (!contenedor || !btnPrev || !btnNext) {
    console.error("No se encontró alguno de los elementos del carrusel en el HTML.");
    return;
  }

  // 3) Generar dinámicamente cada "slide"
  imagenesCarousel.forEach((rutaImg, index) => {
    const slide = document.createElement("div");
    slide.classList.add("slide");
    // Para ocultar todas excepto la primera
    if (index !== 0) slide.classList.add("oculto");

    slide.innerHTML = `
      <img src="${rutaImg}" alt="Evento ${index + 1}" />
      <div class="slide-texto">
        <!-- Este texto es opcional, si quieres mostrar un título sobre la imagen, modifícalo -->
        <h2>Evento ${index + 1}</h2>
      </div>
    `;
    contenedor.appendChild(slide);
  });

  const slides = Array.from(document.querySelectorAll(".slide"));
  let indiceActual = 0;

  function mostrarSlide(nuevoÍndice) {
    slides[indiceActual].classList.add("oculto");
    slides[nuevoÍndice].classList.remove("oculto");
    indiceActual = nuevoÍndice;
  }

  btnPrev.addEventListener("click", () => {
    const sig = (indiceActual - 1 + slides.length) % slides.length;
    mostrarSlide(sig);
  });

  btnNext.addEventListener("click", () => {
    const sig = (indiceActual + 1) % slides.length;
    mostrarSlide(sig);
  });

  // Cambio automático cada 5 segundos (puedes quitarlo o modificar el tiempo)
  setInterval(() => {
    const sig = (indiceActual + 1) % slides.length;
    mostrarSlide(sig);
  }, 5000);
});
