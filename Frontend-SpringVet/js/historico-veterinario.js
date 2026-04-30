verificarLogin();
//-----------------------
//--- PEGAR ID DA URL ---
//-----------------------
const params = new URLSearchParams(window.location.search);
const veterinarioId = params.get("id");

console.log("ID veterinário:", veterinarioId);

if (!veterinarioId || veterinarioId === "{{id}}") {
  console.error("ID inválido na URL");
}

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

    if (veterinarioId && veterinarioId !== "{{id}}") {
      carregarHistorico();
    }
  })
  .catch(err => console.error("Erro ao carregar template:", err));


//----------------------
//--- BUSCAR CONSULTAS -
//----------------------
function carregarHistorico() {
  fetchComToken(`http://localhost:8080/consultas/veterinario/${veterinarioId}`)
    .then(async (res) => {
      if (!res.ok) {
        const erro = await res.text();
        throw new Error(erro || "Erro ao buscar histórico");
      }
      return res.json();
    })
    .then(consultas => {

      console.log("Consultas recebidas:", consultas);

      if (!Array.isArray(consultas)) return;

      let filtradas = aplicarFiltros(consultas);
      renderConsultas(filtradas);
    })
    .catch(err => console.error("Erro ao buscar histórico:", err));
}


//----------------------
//---- RENDERIZAÇÃO ----
//----------------------
function renderConsultas(consultas) {
  const container = document.querySelector("#historico-veterinario");

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

function formatarValor(valor) {
  if (valor == null) return "-";

  return Number(valor).toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL"
  });
}

