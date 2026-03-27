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