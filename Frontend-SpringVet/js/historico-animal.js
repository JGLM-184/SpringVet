verificarLogin();
//-----------------------
//--- PEGAR ID DA URL ---
//-----------------------
const params = new URLSearchParams(window.location.search);
const animalId = params.get("id");

//-------------------------
//--- TEMPLATE CONSULTA ---
//-------------------------
let templateCard = "";

//-------------------------
//--- CARREGAR TEMPLATE ---
//-------------------------
fetch("/components/card-consulta.html")
  .then(response => response.text())
  .then(template => {
    templateCard = template;

    if (animalId) {
      carregarDadosAnimal();
      carregarHistorico();
    }
  })
  .catch(err => console.error("Erro ao carregar template:", err));


//----------------------
//--- BUSCAR ANIMAL ----
//----------------------
function carregarDadosAnimal() {
  fetchComToken(`http://localhost:8080/animal/${animalId}`)
    .then(res => res.json())
    .then(animal => preencherDadosAnimal(animal))
    .catch(err => console.error("Erro ao buscar animal:", err));
}


//----------------------
//--- PREENCHER ANIMAL -
//----------------------
function preencherDadosAnimal(a) {
  document.querySelector("#animal-nome").textContent = a.nome || "-";
  document.querySelector("#animal-especie").textContent = a.especie || "-";
  document.querySelector("#animal-raca").textContent = a.raca || "-";
  document.querySelector("#animal-cor").textContent = a.cor || "-";
  document.querySelector("#animal-sexo").textContent = a.sexo || "-";
  document.querySelector("#animal-castrado").textContent = a.castrado ? "Sim" : "Não";
  document.querySelector("#animal-nascimento").textContent = formatarData(a.nasc);
  document.querySelector("#animal-tutor").textContent = a.nomeTutor || "-";
  configurarBotaoVoltar(a.tutorId);
  console.log("Tutor ID:", a.tutorId);
}


//----------------------
//--- BUSCAR CONSULTAS -
//----------------------
function carregarHistorico() {
  fetchComToken(`http://localhost:8080/consultas/animal/${animalId}`)
    .then(res => res.json())
    .then(consultas => {
      let filtradas = aplicarFiltros(consultas);
      renderConsultas(filtradas);
    })
    .catch(err => console.error("Erro ao buscar histórico:", err));
}


//----------------------
//---- RENDERIZAÇÃO ----
//----------------------
function renderConsultas(consultas) {
  const container = document.querySelector("#historico-animal");

  if (!container) return;

  container.innerHTML = "";

  if (!consultas || consultas.length === 0) {
    container.innerHTML = "<p>Nenhuma consulta encontrada.</p>";
    return;
  }

  consultas.sort((a, b) => new Date(b.dataHora) - new Date(a.dataHora));

  consultas.forEach(consulta => {
    let card = templateCard;

    card = card.replace(/{{id}}/g, consulta.id);
    card = card.replace(/{{dataHora}}/g, formatarDataHora(consulta.dataHora));
    card = card.replace(/{{status}}/g, consulta.status || "-");
    card = card.replace(/{{statusClass}}/g, getStatusClass(consulta.status));
    card = card.replace(/{{motivo}}/g, consulta.motivo || "-");
    card = card.replace(/{{animal}}/g, consulta.animalNome || "-");
    card = card.replace(/{{tutor}}/g, consulta.tutorNome || "-");
    card = card.replace(/{{veterinario}}/g, consulta.veterinarioNome || "-");
    card = card.replace(/{{valor}}/g, formatarValor(consulta.valor));

    container.insertAdjacentHTML("beforeend", card);
  });
}


//----------------------
//---- FILTROS ---------
//----------------------
const selectStatus = document.querySelector("#status");
const inputData = document.querySelector('input[type="date"]');

function aplicarFiltros(consultas) {
  const status = selectStatus?.value;
  const data = inputData?.value;

  return consultas.filter(c => {

    if (status && c.status !== status) {
      return false;
    }

    if (data) {
      const dataConsulta = new Date(c.dataHora).toISOString().split("T")[0];

      if (dataConsulta !== data) {
        return false;
      }
    }

    return true;
  });
}


//----------------------
//------ EVENTOS -------
//----------------------
if (selectStatus) {
  selectStatus.addEventListener("change", carregarHistorico);
}

if (inputData) {
  inputData.addEventListener("change", carregarHistorico);
}


//----------------------
//---- UTILIDADES ------
//----------------------
function getStatusClass(status) {
  if (!status) return "";

  switch (status) {
    case "AGENDADA":
      return "status-agendada";
    case "FINALIZADA":
      return "status-finalizada";
    case "CANCELADA":
      return "status-cancelada";
    default:
      return "";
  }
}

function formatarDataHora(dataHora) {
  if (!dataHora) return "-";

  const data = new Date(dataHora);

  return data.toLocaleString("pt-BR", {
    dateStyle: "short",
    timeStyle: "short"
  });
}

function formatarData(dataStr) {
  if (!dataStr) return "-";

  const [ano, mes, dia] = dataStr.split("-");

  return `${dia}/${mes}/${ano}`;
}

function formatarValor(valor) {
  if (valor == null) return "-";

  return Number(valor).toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL"
  });
}

function configurarBotaoVoltar(tutorId) {
  const link = document.querySelector(".voltar a");

  if (link) {
    link.href = `/pages/tutores-dados.html?id=${tutorId}`;
  }
}