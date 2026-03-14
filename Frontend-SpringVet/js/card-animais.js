fetch("../components/card-animal.html")
  .then(response => response.text())
  .then(data => {
    const container = document.querySelector(".flex-box-animais");

    for (let i = 0; i < 6; i++) {
      container.innerHTML += data;
    }
  });