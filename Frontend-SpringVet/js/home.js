let templateCard = "";
let templateCardCompact = "";

//-------------------------
//--- CARREGAR TEMPLATES --
//-------------------------
Promise.all([
  fetch("../components/card-consulta.html").then(r => r.text()),
  fetch("../components/card-consulta-compact.html").then(r => r.text())
])
.then(([templateNormal, templateCompact]) => {
  templateCard = templateNormal;
  templateCardCompact = templateCompact;

  if (document.querySelector(".flex-box-consultas")) {
    carregarConsultasHoje();
    carregarDashboard();
  }
})
.catch(err => {
  console.error("Erro ao carregar templates:", err);
});


//----------------------
//--- CONSULTAS HOJE ---
//----------------------
function carregarConsultasHoje() {
  fetch("http://localhost:8080/consultas/hoje")
    .then(res => res.json())
    .then(consultas => {
      renderConsultasHoje(consultas);
    })
    .catch(err => {
      console.error("Erro ao buscar consultas de hoje:", err);
    });
}


//----------------------
//-- CONSULTAS ATRASADAS
//----------------------
function carregarConsultasAtrasadas() {
  fetch("http://localhost:8080/consultas/atrasadas")
    .then(res => res.json())
    .then(consultas => {
      renderConsultasAtrasadas(consultas);
    })
    .catch(err => {
      console.error("Erro ao buscar consultas atrasadas:", err);
    });
}


//----------------------
//---- RENDER HOJE ------
//----------------------
function renderConsultasHoje(consultas) {
  const container = document.querySelector(".lista-consultas");

  if (!container) return;

  container.innerHTML = "";

  if (consultas.length === 0) {
    container.innerHTML = "<p>Nenhuma consulta hoje.</p>";
    return;
  }

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
//-- RENDER ATRASADAS ---
//----------------------
function renderConsultasAtrasadas(consultas) {
  const container = document.querySelector("#lista-atrasos");

  if (!container) return;

  container.innerHTML = "";

  if (consultas.length === 0) {
    container.innerHTML = "<p>Nenhuma consulta em atraso.</p>";
    return;
  }

  consultas.forEach(consulta => {
    let card = templateCardCompact;

    card = card.replace(/{{id}}/g, consulta.id);
    card = card.replace(/{{dataHora}}/g, formatarDataHora(consulta.dataHora));
    card = card.replace(/{{status}}/g, consulta.status || "-");
    card = card.replace(/{{statusClass}}/g, getStatusClass(consulta.status));
    card = card.replace(/{{animal}}/g, consulta.animalNome || "-");
    card = card.replace(/{{veterinario}}/g, consulta.veterinarioNome || "-");

    container.insertAdjacentHTML("beforeend", card);
  });
}


//----------------------
//---- DASHBOARD -------
//----------------------
function carregarDashboard() {
  carregarTotalHoje();
  carregarTotalConsultas();
  carregarTotalTutores();
  carregarTotalAnimais();
  carregarTotalVets();
  carregarConsultasAtrasadas();
}


//----------------------
//---- TOTAIS ----------
//----------------------
function carregarTotalHoje() {
  fetch("http://localhost:8080/consultas/total/hoje")
    .then(res => res.json())
    .then(total => {
      const el = document.querySelector("#total-hoje");
      if (el) el.textContent = total;
    })
    .catch(err => console.error("Erro total hoje:", err));
}

function carregarTotalConsultas() {
  fetch("http://localhost:8080/consultas/total")
    .then(res => res.json())
    .then(total => {
      const el = document.querySelector("#total-consultas");
      if (el) el.textContent = total;
    })
    .catch(err => console.error("Erro total consultas:", err));
}

function carregarTotalTutores() {
  fetch("http://localhost:8080/tutor/total")
    .then(res => res.json())
    .then(total => {
      const el = document.querySelector("#total-tutores");
      if (el) el.textContent = total;
    })
    .catch(err => console.error("Erro total tutores:", err));
}

function carregarTotalAnimais() {
  fetch("http://localhost:8080/animal/total")
    .then(res => res.json())
    .then(total => {
      const el = document.querySelector("#total-animais");
      if (el) el.textContent = total;
    })
    .catch(err => console.error("Erro total animais:", err));
}

function carregarTotalVets() {
  fetch("http://localhost:8080/veterinario/total")
    .then(res => res.json())
    .then(total => {
      const el = document.querySelector("#total-vets");
      if (el) el.textContent = total;
    })
    .catch(err => console.error("Erro total veterinários:", err));
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