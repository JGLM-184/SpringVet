fetch("../components/card-consulta.html")
  .then(response => response.text())
  .then(data => {
    const container = document.querySelector(".flex-box-consultas");

    for (let i = 0; i < 1; i++) {
      container.innerHTML += data;
    }
  });