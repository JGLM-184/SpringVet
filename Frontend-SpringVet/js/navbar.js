fetch("../components/navbar.html")
  .then(response => response.text())
  .then(data => {
    document.getElementById("navbar").innerHTML = data;

    const btnLogout = document.getElementById("btn-logout");

    if (btnLogout) {
      btnLogout.addEventListener("click", function(e){
        e.preventDefault();
        e.stopPropagation();
        logout();
      });
    }
});