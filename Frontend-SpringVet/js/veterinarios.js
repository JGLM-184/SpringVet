let templateCardVet = "";

//-------------------------
//--- CARREGAR TEMPLATE ---
//-------------------------
fetch("../components/card-veterinario.html")
  .then(response => response.text())
  .then(template => {
    templateCardVet = template;

    if (document.querySelector(".flex-box-veterinarios")) {
      buscarVeterinarios();
    }
  })
  .catch(err => {
    console.error("Erro ao carregar template:", err);
  });


//-------------------------
//------ ELEMENTOS --------
//-------------------------
const inputNomeVet = document.querySelector("#input-nome-vet");
const selectEspecialidade = document.querySelector("#select-especialidade");
const checkInativos = document.querySelector("#check-inativos");
const btnBuscarVet = document.querySelector("#btn-buscar-vet");


//----------------------
//--- RENDER CARDS -----
//----------------------
function renderVeterinarios(veterinarios) {
  const container = document.querySelector(".flex-box-veterinarios");
  if (!container) return;

  container.innerHTML = "";

  if (!veterinarios || veterinarios.length === 0) {
    container.innerHTML = "<p>Nenhum veterinário encontrado.</p>";
    return;
  }

  veterinarios.forEach(vet => {
    const statusTexto = vet.ativo ? "Ativo" : "Inativo";
    const statusClass = vet.ativo ? "ativo" : "inativo";

    let cardHTML = templateCardVet
      .replace("{{nome}}", vet.nome)
      .replace("{{especialidade}}", vet.especialidade)
      .replace("{{telefone}}", vet.telefone || "—")
      .replace("{{id}}", vet.id)
      .replace("{{status}}", statusTexto)
      .replace("{{statusClass}}", statusClass);

    container.innerHTML += cardHTML;
  });
}


//-------------------------
//------ BUSCAR -----------
//-------------------------
function buscarVeterinarios() {
  const nome = inputNomeVet?.value.trim();
  const especialidade = selectEspecialidade?.value;
  const incluirInativos = checkInativos?.checked;

  let url = "http://localhost:8080/veterinario/buscar?";
  const params = [];

  if (nome) params.push(`nome=${encodeURIComponent(nome)}`);
  if (especialidade) params.push(`especialidade=${encodeURIComponent(especialidade)}`);

  if (!incluirInativos) {
    params.push(`status=Ativo`);
  }

  url += params.join("&");

  fetch(url)
    .then(res => res.json())
    .then(veterinarios => {
      renderVeterinarios(veterinarios);
    })
    .catch(err => console.error("Erro ao buscar veterinários:", err));
}


//----------------------
//------ EVENTOS -------
//----------------------
if (btnBuscarVet) {
  btnBuscarVet.addEventListener("click", buscarVeterinarios);
}

if (inputNomeVet) {
  inputNomeVet.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      buscarVeterinarios();
    }
  });
}

if (checkInativos) {
  checkInativos.addEventListener("change", buscarVeterinarios);
}

if (selectEspecialidade) {
  selectEspecialidade.addEventListener("change", buscarVeterinarios);
}