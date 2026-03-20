fetch("../components/card-veterinario.html")
  .then(response => response.text())
  .then(data => {
    const container = document.querySelector(".flex-box-veterinarios");

    for (let i = 0; i < 8; i++) {
      container.innerHTML += data;
    }
  });