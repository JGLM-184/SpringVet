//-----------------------
//--- PEGAR ID DA URL ---
//-----------------------
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

//----------------------
//--- BUSCAR TUTOR -----
//----------------------
fetch(`http://localhost:8080/tutor/${id}`)
  .then((res) => res.json())
  .then((tutor) => {
    preencherDados(tutor);
  })
  .catch((err) => console.error("Erro ao buscar tutor:", err));

//----------------------
//--- PREENCHER HTML ---
//----------------------
function preencherDados(tutor) {
  document.querySelector("#nome").textContent = tutor.nome || "-";
  document.querySelector("#cpf").textContent = tutor.cpf || "-";
  document.querySelector("#email").textContent = tutor.email || "-";
  document.querySelector("#telefone").textContent = tutor.telefone || "-";
}

//--------------------------
//--- MODAL EDITAR TUTOR ---
//--------------------------
const formEditar = document.querySelector("#modal-editar-tutor form");

if (formEditar) {
  formEditar.addEventListener("submit", function (e) {
    e.preventDefault();

    const dados = {
      nome: formEditar.nome.value,
      cpf: formEditar.cpf.value,
      email: formEditar.email.value,
      telefone: formEditar.telefone.value,
    };

    atualizarTutor(dados);
  });
}

function atualizarTutor(dados) {
  fetch(`http://localhost:8080/tutor/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(dados),
  })
    .then((res) => res.json())
    .then((tutorAtualizado) => {
      preencherDados(tutorAtualizado);

      document.querySelector("#modal-editar-tutor").close();

      alert("Tutor atualizado com sucesso!");
    })
    .catch((err) => console.error("Erro ao atualizar:", err));
}

const btnExcluir = document.querySelector("#btn-excluir-tutor");

if (btnExcluir) {
  btnExcluir.addEventListener("click", () => {
    const confirmar = confirm("Tem certeza que deseja excluir este tutor?");

    if (!confirmar) return;

    excluirTutor();
  });
}

function excluirTutor() {
  fetch(`http://localhost:8080/tutor/${id}`, {
    method: "DELETE",
  })
    .then(() => {
      alert("Tutor excluído com sucesso!");

      window.location.href = "/pages/tutores.html";
    })
    .catch((err) => console.error("Erro ao excluir:", err));
}

//------------------------------
//--- ABRIR MODAL CONSULTA -----
//------------------------------
document.addEventListener("click", (e) => {
  const botao = e.target.closest(".btn-nova-consulta");

  if (botao) {
    const animalId = botao.dataset.id;
    const animalNome = botao.dataset.nome;

    const modal = document.getElementById("modal-nova-consulta");

    const inputAnimal = modal.querySelector('input[name="animal"]');
    inputAnimal.value = animalNome;

    modal.dataset.animalId = animalId;

    carregarVeterinarios();

    modal.showModal();
  }
});

function carregarVeterinarios() {
  fetch("http://localhost:8080/veterinario")
    .then((res) => res.json())
    .then((vets) => {
      const select = document.querySelector('select[name="veterinario"]');

      if (!select) return;

      select.innerHTML =
        '<option value="" disabled selected>Veterinário</option>';

      vets.forEach((vet) => {
        if (!vet.ativo) return;

        const option = document.createElement("option");
        option.value = vet.id;
        option.textContent = `${vet.nome} - ${vet.especialidade}`;

        select.appendChild(option);
      });
    })
    .catch((err) => console.error("Erro ao buscar veterinários:", err));
}

//------------------------------
//--- FORM NOVA CONSULTA -------
//------------------------------
const formNovaConsulta = document.querySelector("#modal-nova-consulta form");

if (formNovaConsulta) {
  formNovaConsulta.addEventListener("submit", function (e) {
    e.preventDefault();

    const modal = document.getElementById("modal-nova-consulta");

    const animalId = modal.dataset.animalId;
    const veterinarioId = formNovaConsulta.veterinario.value;

    const dados = {
      dataHora: formNovaConsulta["data-hora"].value,
      motivo: formNovaConsulta.motivo.value,
      observacao: formNovaConsulta.observacao.value,
      valor: parseFloat(formNovaConsulta.valor.value),
      formaPagamento: formNovaConsulta["forma-pagamento"].value,
    };

    criarConsulta(animalId, veterinarioId, dados);
  });
}

function criarConsulta(animalId, veterinarioId, dados) {
  fetch(
    `http://localhost:8080/consultas/animal/${animalId}/veterinario/${veterinarioId}`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dados),
    },
  )
    .then(async (res) => {
      if (!res.ok) {
        const erro = await res.text();
        throw new Error(erro || "Erro ao criar consulta");
      }
      return res.json();
    })
    .then(() => {
      document.getElementById("modal-nova-consulta").close();

      alert("Consulta criada com sucesso!");

      location.reload();
    })
    .catch((err) => {
      console.error("Erro ao criar consulta:", err);
      alert(err.message);
    });
}
