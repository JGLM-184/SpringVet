fetch("../components/card-tutor.html")
  .then(response => response.text())
  .then(data => {
    const container = document.querySelector(".flex-box-tutores");

    for (let i = 0; i < 16; i++) {
      container.innerHTML += data;
    }
  });