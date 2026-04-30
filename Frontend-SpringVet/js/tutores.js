verificarLogin();

let templateCard = "";

//-------------------------
//--- CARREGAR TEMPLATE ---
//-------------------------
fetch("../components/card-tutor.html")
  .then(response => response.text())
  .then(template => {
    templateCard = template;

    if (document.querySelector(".flex-box-tutores")) {
      carregarTutores();
    }
  })
  .catch(err => {
    console.error("Erro ao carregar template:", err);
  });


//----------------------
//--- CARREGAR TODOS ---
//----------------------
function carregarTutores() {
  fetchComToken("http://localhost:8080/tutor")
    .then(res => res.json())
    .then(tutores => {
      renderTutores(tutores);
    })
    .catch(err => {
      console.error("Erro ao buscar tutores:", err);
    });
}


//----------------------
//---- RENDERIZAÇÃO ----
//----------------------
function renderTutores(tutores) {
  const container = document.querySelector(".flex-box-tutores");

  if (!container) return;

  container.innerHTML = "";

  tutores.forEach(tutor => {
    let card = templateCard;

    card = card.replace(/{{nome}}/g, tutor.nome || "Sem nome");
    card = card.replace(/{{email}}/g, tutor.email || "-");
    card = card.replace(/{{telefone}}/g, tutor.telefone || "-");
    card = card.replace(/{{id}}/g, tutor.id);

    container.insertAdjacentHTML("beforeend", card);
  });
}


//-----------------------
//--- FILTRO POR NOME ---
//-----------------------
const inputNome = document.querySelector("#nome");
const btnBuscar = document.querySelector(".btn-outline");

function buscarTutores() {
  const nomeFiltro = inputNome?.value.trim();

  if (inputNome && !nomeFiltro) {
    carregarTutores();
    return;
  }

  fetchComToken(`http://localhost:8080/tutor/buscar?nome=${encodeURIComponent(nomeFiltro)}`)
    .then(res => res.json())
    .then(tutores => {

      const container = document.querySelector(".flex-box-tutores");

      if (!container) return;

      if (tutores.length === 0) {
        container.innerHTML = "<p>Nenhum tutor encontrado.</p>";
        return;
      }

      renderTutores(tutores);
    })
    .catch(err => console.error("Erro na busca:", err));
}


//----------------------
//------ EVENTOS -------
//----------------------

if (btnBuscar) {
  btnBuscar.addEventListener("click", buscarTutores);
}

if (inputNome) {
  inputNome.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
      buscarTutores();
    }
  });
}
