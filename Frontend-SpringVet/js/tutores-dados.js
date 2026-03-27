//-----------------------
//--- PEGAR ID DA URL ---
//-----------------------
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

//----------------------
//--- BUSCAR TUTOR -----
//----------------------
fetch(`http://localhost:8080/tutor/${id}`)
  .then(res => res.json())
  .then(tutor => {
    preencherDados(tutor);
  })
  .catch(err => console.error("Erro ao buscar tutor:", err));


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
            telefone: formEditar.telefone.value
        };

        atualizarTutor(dados);
    });
}

function atualizarTutor(dados) {
    fetch(`http://localhost:8080/tutor/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    })
    .then(res => res.json())
    .then(tutorAtualizado => {

        preencherDados(tutorAtualizado);

        document.querySelector("#modal-editar-tutor").close();

        alert("Tutor atualizado com sucesso!");
    })
    .catch(err => console.error("Erro ao atualizar:", err));
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
        method: "DELETE"
    })
    .then(() => {

        alert("Tutor excluído com sucesso!");

        window.location.href = "/pages/tutores.html";
    })
    .catch(err => console.error("Erro ao excluir:", err));
}