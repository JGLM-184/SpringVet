//-----------------------
//--- PEGAR ID DA URL ---
//-----------------------
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

//----------------------
//--- VAR GLOBAL -------
//----------------------
let consultaAtual = null;

//----------------------
//--- BUSCAR CONSULTA ---
//----------------------
fetch(`http://localhost:8080/consultas/${id}`)
  .then(res => res.json())
  .then(consulta => {
    consultaAtual = consulta;
    preencherDados(consulta);
  })
  .catch(err => console.error("Erro ao buscar consulta:", err));


//----------------------
//--- PREENCHER HTML ---
//----------------------
function preencherDados(c) {

  // ===== ANIMAL =====
  document.querySelector("#animal-nome").textContent = c.animalNome || "-";
  document.querySelector("#animal-especie").textContent = c.animalEspecie || "-";
  document.querySelector("#animal-raca").textContent = c.animalRaca || "-";
  document.querySelector("#animal-cor").textContent = c.animalCor || "-";
  document.querySelector("#animal-sexo").textContent = c.animalSexo || "-";
  document.querySelector("#animal-castrado").textContent = c.animalCastrado ? "Sim" : "Não";
  document.querySelector("#animal-nascimento").textContent = formatarData(c.animalNasc);
  document.querySelector("#animal-tutor").textContent = c.tutorNome || "-";

  document.querySelector("#vet-nome").textContent = c.veterinarioNome || "-";
  document.querySelector("#vet-especialidade").textContent = c.veterinarioEspecialidade || "-";
  document.querySelector("#vet-crmv").textContent = c.veterinarioCrmv || "-";
  document.querySelector("#vet-email").textContent = c.veterinarioEmail || "-";
  document.querySelector("#vet-telefone").textContent = c.veterinarioTelefone || "-";

  const statusEl = document.querySelector("#status-consulta");
  statusEl.textContent = c.status || "-";
  statusEl.className = "status " + getStatusClass(c.status);

  document.querySelector("#consulta-data").textContent = formatarDataHora(c.dataHora);
  document.querySelector("#consulta-motivo").textContent = c.motivo || "-";
  document.querySelector("#consulta-observacao").textContent = c.observacao || "-";

  document.querySelector("#consulta-valor").textContent = formatarValor(c.valor);
  document.querySelector("#consulta-forma").textContent = c.formaPagamento || "-";

  const pagaEl = document.querySelector("#consulta-paga");
  pagaEl.textContent = c.paga ? "Sim" : "Não";
  pagaEl.className = "status " + (c.paga ? "status-finalizada" : "status-cancelada");

  document.querySelector("#consulta-criacao").textContent = formatarData(c.dataCriacao);
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


//----------------------
//--- FORMATAR INPUT ---
//----------------------
function formatarParaInput(dataHora) {
  if (!dataHora) return "";

  const data = new Date(dataHora);

  const yyyy = data.getFullYear();
  const mm = String(data.getMonth() + 1).padStart(2, '0');
  const dd = String(data.getDate()).padStart(2, '0');
  const hh = String(data.getHours()).padStart(2, '0');
  const min = String(data.getMinutes()).padStart(2, '0');

  return `${yyyy}-${mm}-${dd}T${hh}:${min}`;
}


//------------------------------
//--- CARREGAR VETERINÁRIOS ----
//------------------------------
function carregarVeterinarios(select, selecionadoId) {
  fetch("http://localhost:8080/veterinario")
    .then(res => res.json())
    .then(vets => {

      select.innerHTML = '<option disabled>Veterinário</option>';

      vets.forEach(vet => {
        if (!vet.ativo) return;

        const option = document.createElement("option");
        option.value = vet.id;
        option.textContent = `${vet.nome} - ${vet.especialidade}`;

        if (vet.id === selecionadoId) {
          option.selected = true;
        }

        select.appendChild(option);
      });
    })
    .catch(err => console.error("Erro ao carregar veterinários:", err));
}


//------------------------------
//--- ABRIR MODAL EDITAR -------
//------------------------------
const btnEditarConsulta = document.getElementById("btn-editar-consulta");

if (btnEditarConsulta) {
  btnEditarConsulta.addEventListener("click", () => {

    if (!consultaAtual) return;

    const modal = document.getElementById("modal-editar-consulta");

    modal.querySelector('input[name="animal"]').value = consultaAtual.animalNome;

    const select = modal.querySelector('select[name="veterinario"]');
    carregarVeterinarios(select, consultaAtual.veterinarioId);

    modal.querySelector('input[name="data-hora"]').value = formatarParaInput(consultaAtual.dataHora);
    modal.querySelector('input[name="motivo"]').value = consultaAtual.motivo;
    modal.querySelector('input[name="observacao"]').value = consultaAtual.observacao;
    modal.querySelector('input[name="valor"]').value = consultaAtual.valor;
    modal.querySelector('input[name="forma-pagamento"]').value = consultaAtual.formaPagamento;

    modal.showModal();
  });
}


//------------------------------
//--- SUBMIT EDITAR ------------
//------------------------------
const formEditarConsulta = document.querySelector("#modal-editar-consulta form");

if (formEditarConsulta) {
  formEditarConsulta.addEventListener("submit", function (e) {
    e.preventDefault();

    const veterinarioId = formEditarConsulta.veterinario.value;

    const dados = {
      dataHora: formEditarConsulta["data-hora"].value,
      motivo: formEditarConsulta.motivo.value,
      observacao: formEditarConsulta.observacao.value,
      valor: parseFloat(formEditarConsulta.valor.value),
      formaPagamento: formEditarConsulta["forma-pagamento"].value,
      paga: consultaAtual.paga
    };

    atualizarConsulta(veterinarioId, dados);
  });
}


//------------------------------
//--- PUT CONSULTA -------------
//------------------------------
function atualizarConsulta(veterinarioId, dados) {
  fetch(`http://localhost:8080/consultas/${id}/veterinario/${veterinarioId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(dados)
  })
    .then(async (res) => {
      if (!res.ok) {
        const erro = await res.text();
        throw new Error(erro || "Erro ao atualizar consulta");
      }
      return res.json();
    })
    .then((consultaAtualizada) => {

      consultaAtual = consultaAtualizada;

      preencherDados(consultaAtualizada);

      document.getElementById("modal-editar-consulta").close();

      alert("Consulta atualizada com sucesso!");
    })
    .catch(err => {
      console.error("Erro ao atualizar consulta:", err);
      alert(err.message);
    });
}

//------------------------------
//--- CANCELAR CONSULTA --------
//------------------------------

const btnCancelarConsulta = document.getElementById("btn-cancelar-consulta");

if (btnCancelarConsulta) {
  btnCancelarConsulta.addEventListener("click", () => {

    if (!consultaAtual) return;

    if (consultaAtual.status === "CANCELADA") {
      alert("Consulta já está cancelada.");
      return;
    }

    if (consultaAtual.status === "FINALIZADA") {
      alert("Não é possível cancelar uma consulta finalizada.");
      return;
    }

    const confirmar = confirm("Tem certeza que deseja cancelar esta consulta?");
    if (!confirmar) return;

    cancelarConsulta();
  });
}

function cancelarConsulta() {
  fetch(`http://localhost:8080/consultas/${id}/cancelar`, {
    method: "PUT"
  })
    .then(async (res) => {
      if (!res.ok) {
        const erro = await res.text();
        throw new Error(erro || "Erro ao cancelar consulta");
      }
    })
    .then(() => {
      alert("Consulta cancelada com sucesso!");
      location.reload();
    })
    .catch(err => {
      console.error("Erro ao cancelar:", err);
      alert(err.message);
    });
}



//------------------------------
//--- FINALIZAR CONSULTA -------
//------------------------------

const btnFinalizarConsulta = document.getElementById("btn-finalizar-consulta");

if (btnFinalizarConsulta) {
  btnFinalizarConsulta.addEventListener("click", () => {

    if (!consultaAtual) return;

    if (consultaAtual.status === "FINALIZADA") {
      alert("Consulta já está finalizada.");
      return;
    }

    if (consultaAtual.status === "CANCELADA") {
      alert("Não é possível finalizar uma consulta cancelada.");
      return;
    }

    const confirmar = confirm("Deseja finalizar esta consulta?");
    if (!confirmar) return;

    finalizarConsulta();
  });
}

function finalizarConsulta() {
  fetch(`http://localhost:8080/consultas/${id}/finalizar`, {
    method: "PUT"
  })
    .then(async (res) => {
      if (!res.ok) {
        const erro = await res.text();
        throw new Error(erro || "Erro ao finalizar consulta");
      }
    })
    .then(() => {
      alert("Consulta finalizada com sucesso!");
      location.reload();
    })
    .catch(err => {
      console.error("Erro ao finalizar:", err);
      alert(err.message);
    });
}