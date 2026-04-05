//-----------------------
//--- PEGAR ID DA URL ---
//-----------------------
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

let veterinarioAtual = null;

//----------------------------
//---- BUSCAR VETERINÁRIO ----
//----------------------------
if (id) {
    fetch(`http://localhost:8080/veterinario/${id}`)
        .then(res => res.json())
        .then(veterinario => {
            veterinarioAtual = veterinario;
            preencherDados(veterinario);
        })
        .catch(err => console.error("Erro ao buscar veterinário:", err));
}

//----------------------
//--- PREENCHER HTML ---
//----------------------
function preencherDados(veterinario) {
    document.querySelector("#nome").textContent = veterinario.nome || "-";
    document.querySelector("#especialidade").textContent = veterinario.especialidade || "-";
    document.querySelector("#crmv").textContent = veterinario.crmv || "-";
    document.querySelector("#email").textContent = veterinario.email || "-";
    document.querySelector("#telefone").textContent = veterinario.telefone || "-";

    document.querySelector("#status").textContent =
        veterinario.ativo ? "Ativo" : "Inativo";

    atualizarBotaoStatus(veterinario);
}

//----------------------
//--- BOTÃO STATUS -----
//----------------------
function atualizarBotaoStatus(veterinario) {
    const btn = document.getElementById("btn-status-veterinario");
    if (!btn) return;

    const icon = btn.querySelector("span");

    if (veterinario.ativo) {
        btn.className = "btn btn-danger";
        icon.textContent = "block";
    } else {
        btn.className = "btn btn-primary";
        icon.textContent = "check_circle";
    }

    const novoBtn = btn.cloneNode(true);
    btn.replaceWith(novoBtn);

    novoBtn.addEventListener("click", () => {
        alterarStatus(veterinarioAtual);
    });
}

//--------------
//--- STATUS ---
//--------------
function alterarStatus(veterinario) {
    const acao = veterinario.ativo ? "inativar" : "ativar";

    fetch(`http://localhost:8080/veterinario/${veterinario.id}/${acao}`, {
        method: "PUT"
    })
    .then(() => {

        veterinario.ativo = !veterinario.ativo;

        document.querySelector("#status").textContent =
            veterinario.ativo ? "Ativo" : "Inativo";

        atualizarBotaoStatus(veterinario);

        alert(`Veterinário ${veterinario.ativo ? "ativado" : "inativado"} com sucesso!`);
    })
    .catch(err => console.error("Erro ao alterar status:", err));
}

//--------------------------------
//--- MODAL EDITAR VETERINARIO ---
//--------------------------------
const modalVeterinario = document.getElementById("modal-editar-veterinario");
const btnEditarVeterinario = document.getElementById("btn-editar-veterinario");

if (btnEditarVeterinario && modalVeterinario) {
    btnEditarVeterinario.addEventListener("click", () => {

        modalVeterinario.querySelector('input[name="nome"]').value =
            document.querySelector("#nome").textContent;

        modalVeterinario.querySelector('select[name="especialidade"]').value =
            document.querySelector("#especialidade").textContent;

        modalVeterinario.querySelector('input[name="crmv"]').value =
            document.querySelector("#crmv").textContent;

        modalVeterinario.querySelector('input[name="email"]').value =
            document.querySelector("#email").textContent;

        modalVeterinario.querySelector('input[name="telefone"]').value =
            document.querySelector("#telefone").textContent;

        modalVeterinario.showModal();
    });
}

//----------------------
//--- EDITAR (PUT) -----
//----------------------
const formEditarVet = document.querySelector("#modal-editar-veterinario form");

if (formEditarVet) {
    formEditarVet.addEventListener("submit", function (e) {
        e.preventDefault();

        const dados = {
            nome: formEditarVet.nome.value.trim(),
            especialidade: formEditarVet.especialidade.value,
            crmv: formEditarVet.crmv.value.trim(),
            telefone: formEditarVet.telefone.value.trim(),
            email: formEditarVet.email.value.trim(),
        };

        atualizarVeterinario(id, dados);
    });
}

//----------------------
//--- ATUALIZAR --------
//----------------------
function atualizarVeterinario(id, dados) {
    fetch(`http://localhost:8080/veterinario/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    })
    .then(res => res.json())
    .then(veterinarioAtualizado => {

        veterinarioAtual = veterinarioAtualizado;

        preencherDados(veterinarioAtualizado);

        modalVeterinario.close();

        alert("Veterinário atualizado com sucesso!");
    })
    .catch(err => console.error("Erro ao atualizar:", err));
}

//-------------------------
//--- TEMPLATE CONSULTA ---
//-------------------------
let templateCard = "";

//-------------------------
//--- CARREGAR TEMPLATE ---
//-------------------------
fetch("../components/card-consulta.html")
  .then(response => response.text())
  .then(template => {
    templateCard = template;

    if (id) {
      carregarAgenda();
    }
  })
  .catch(err => console.error("Erro ao carregar template:", err));

function carregarAgenda() {
  fetch(`http://localhost:8080/consultas/veterinario/${id}`)
    .then((res) => res.json())
    .then((consultas) => {
      let apenasAgendadas = consultas.filter((c) => c.status === "AGENDADA");
      let filtradas = aplicarFiltrosAgenda(apenasAgendadas);
      renderAgenda(filtradas);
    })
    .catch((err) => console.error("Erro ao buscar agenda:", err));
}

function renderAgenda(consultas) {
  const container = document.querySelector("#agenda");

  if (!container) return;

  container.innerHTML = "";

  if (!consultas || consultas.length === 0) {
    container.innerHTML = "<p>Nenhuma consulta encontrada.</p>";
    return;
  }

  consultas.sort((a, b) => new Date(a.dataHora) - new Date(b.dataHora));

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

const inputBusca = document.querySelector('.input-text[type="search"]');
const inputData = document.querySelector('.input-text-secondary[type="date"]');
const btnBuscar = document.querySelector(".btn-outline-light");

function aplicarFiltrosAgenda(consultas) {
  const texto = inputBusca?.value.toLowerCase().trim();
  const data = inputData?.value;

  return consultas.filter(c => {

    if (texto) {
      const animal = c.animalNome?.toLowerCase() || "";

      if (!animal.includes(texto)) {
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

if (btnBuscar) {
  btnBuscar.addEventListener("click", carregarAgenda);
}

if (inputBusca) {
  inputBusca.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
      carregarAgenda();
    }
  });
}

if (inputData) {
  inputData.addEventListener("change", carregarAgenda);
}

//----------------------
//---- UTILIDADES ------
//----------------------
function formatarDataHora(dataHora) {
  if (!dataHora) return "-";

  const data = new Date(dataHora);

  return data.toLocaleString("pt-BR", {
    dateStyle: "short",
    timeStyle: "short"
  });
}

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

function formatarValor(valor) {
  if (valor == null) return "-";

  return Number(valor).toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL"
  });
}

document.addEventListener("DOMContentLoaded", () => {
    const btnHistorico = document.getElementById("btn-historico");

    if (!btnHistorico) {
        console.error("Botão histórico não encontrado");
        return;
    }

    btnHistorico.addEventListener("click", () => {
        if (!veterinarioAtual) {
            console.error("Veterinário ainda não carregado");
            return;
        }

        console.log("Redirecionando com ID:", veterinarioAtual.id);

        window.location.href = `/pages/historico-veterinario.html?id=${veterinarioAtual.id}`;
    });
});