let templateCard = "";

//-------------------------
//--- CARREGAR TEMPLATE ---
//-------------------------
fetch("../components/card-consulta.html")
  .then(response => response.text())
  .then(template => {
    templateCard = template;

    if (document.querySelector(".flex-box-consultas")) {
      buscarConsultas();
    }
  })
  .catch(err => {
    console.error("Erro ao carregar template:", err);
  });


//----------------------
//--- CARREGAR TODOS ---
//----------------------
function carregarConsultas() {
  fetch("http://localhost:8080/consultas")
    .then(res => res.json())
    .then(consultas => {
      renderConsultas(consultas);
    })
    .catch(err => {
      console.error("Erro ao buscar consultas:", err);
    });
}


//----------------------
//---- RENDERIZAÇÃO ----
//----------------------
function renderConsultas(consultas) {
  const container = document.querySelector(".flex-box-consultas");

  if (!container) return;

  container.innerHTML = "";

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
const inputBusca = document.querySelector(".input-text");
const inputData = document.querySelector(".input-text-secondary");
const checkFinalizada = document.querySelector("#check-finalizada");
const checkCancelada = document.querySelector("#check-cancelada");
const btnBuscar = document.querySelector(".btn-outline");

function buscarConsultas() {
  fetch("http://localhost:8080/consultas")
    .then(res => res.json())
    .then(consultas => {

      let filtradas = aplicarFiltros(consultas);
      renderConsultas(filtradas);

    })
    .catch(err => console.error("Erro na busca:", err));
}

function aplicarFiltros(consultas) {
  const texto = inputBusca?.value.toLowerCase().trim();
  const data = inputData?.value;

  return consultas.filter(c => {

    let statusValido = c.status === "AGENDADA";

    if (checkFinalizada?.checked && c.status === "FINALIZADA") {
      statusValido = true;
    }

    if (checkCancelada?.checked && c.status === "CANCELADA") {
      statusValido = true;
    }

    if (!statusValido) return false;

    if (texto) {
      const animal = c.animalNome?.toLowerCase() || "";
      const vet = c.veterinarioNome?.toLowerCase() || "";

      if (!animal.includes(texto) && !vet.includes(texto)) {
        return false;
      }
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
if (btnBuscar) {
  btnBuscar.addEventListener("click", buscarConsultas);
}

if (inputBusca) {
  inputBusca.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
      buscarConsultas();
    }
  });
}

if (inputData) {
  inputData.addEventListener("change", buscarConsultas);
}

if (checkFinalizada) {
  checkFinalizada.addEventListener("change", buscarConsultas);
}

if (checkCancelada) {
  checkCancelada.addEventListener("change", buscarConsultas);
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