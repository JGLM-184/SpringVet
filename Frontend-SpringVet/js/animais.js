let templateCardAnimal = "";

//-------------------------
//--- CARREGAR TEMPLATE ---
//-------------------------
fetch("../components/card-animal.html")
  .then(response => response.text())
  .then(template => {
    templateCardAnimal = template;

    const params = new URLSearchParams(window.location.search);
    const tutorId = params.get("id");

    if (tutorId) {
        listarAnimaisPorTutor(tutorId);
    }
  })
  .catch(err => console.error("Erro ao carregar template do card de animal:", err));

//----------------------
//---- RENDER CARDS ----
//----------------------
function renderCardAnimal(animal) {
    const container = document.querySelector(".container-cards-animais");
    if (!container) return;

    let cardHTML = templateCardAnimal
        .replace(/{{id}}/g, animal.id)
        .replace(/{{nome}}/g, animal.nome)
        .replace(/{{especie}}/g, animal.especie)
        .replace(/{{raca}}/g, animal.raca)
        .replace(/{{cor}}/g, animal.cor || "—");

    const tempDiv = document.createElement("div");
    tempDiv.innerHTML = cardHTML;
    const cardElement = tempDiv.firstElementChild;

    cardElement.querySelector(".btn-editar-animal")?.addEventListener("click", () => abrirModalEditarAnimal(animal));
    cardElement.querySelector(".btn-historico-animal")?.addEventListener("click", () => abrirHistoricoAnimal(animal));
    cardElement.querySelector(".btn-nova-consulta")?.addEventListener("click", () => abrirModalNovaConsulta(animal));

    container.appendChild(cardElement);
}

async function listarAnimaisPorTutor(tutorId) {
    try {
        const res = await fetch(`http://localhost:8080/animal/tutor/${tutorId}`);
        const animais = await res.json();

        const container = document.querySelector(".container-cards-animais");
        container.innerHTML = "";

        animais.forEach(animal => renderCardAnimal(animal));
    } catch (err) {
        console.error("Erro ao listar animais por tutor:", err);
    }
}

function abrirModalEditarAnimal(animal) { /* ... */ }
function abrirHistoricoAnimal(animal) { /* ... */ }
function abrirModalNovaConsulta(animal) { /* ... */ }



//-------------------------
//------ NOVO ANIMAL ------
//-------------------------
const btnNovoAnimal = document.querySelector("#btn-novo-animal");
const modalNovoAnimal = document.querySelector("#modal-novo-animal");

if (btnNovoAnimal && modalNovoAnimal) {
    btnNovoAnimal.addEventListener("click", () => {
        modalNovoAnimal.showModal();
    });
}

//
const formNovoAnimal = document.querySelector("#modal-novo-animal form");

if (formNovoAnimal) {
    formNovoAnimal.addEventListener("submit", function (e) {
        e.preventDefault();

        const dados = {
            nome: formNovoAnimal.nome.value,
            especie: formNovoAnimal.especie.value,
            raca: formNovoAnimal.raca.value,
            cor: formNovoAnimal.cor.value,
            sexo: formNovoAnimal.sexo.value,
            nasc: formNovoAnimal.nasc.value,
            castrado: formNovoAnimal.castrado.value === "true"
        };

        criarAnimal(dados);
    });
}

//
async function criarAnimal(dados) {
    try {
        const params = new URLSearchParams(window.location.search);
        const tutorId = params.get("id");

        const res = await fetch(`http://localhost:8080/animal/${tutorId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dados)
        });

        const novoAnimal = await res.json();

        document.querySelector("#modal-novo-animal").close();

        document.querySelector("#modal-novo-animal form").reset();

        listarAnimaisPorTutor(tutorId);

        alert("Animal cadastrado com sucesso!");

    } catch (err) {
        console.error("Erro ao criar animal:", err);
    }
}

//
async function criarAnimal(dados) {
    try {
        const params = new URLSearchParams(window.location.search);
        const tutorId = params.get("id");

        const res = await fetch(`http://localhost:8080/animal/${tutorId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dados)
        });

        const novoAnimal = await res.json();

        document.querySelector("#modal-novo-animal").close();

        document.querySelector("#modal-novo-animal form").reset();

        listarAnimaisPorTutor(tutorId);

        alert("Animal cadastrado com sucesso!");

    } catch (err) {
        console.error("Erro ao criar animal:", err);
    }
}


//-------------------------
//----- EDITAR ANIMAL -----
//-------------------------
let animalSelecionadoId = null;

function abrirModalEditarAnimal(animal) {
    const modal = document.getElementById("modal-editar-animal");

    animalSelecionadoId = animal.id;

    modal.querySelector('input[name="nome"]').value = animal.nome;
    modal.querySelector('select[name="especie"]').value = animal.especie;
    modal.querySelector('input[name="raca"]').value = animal.raca;
    modal.querySelector('input[name="cor"]').value = animal.cor;
    modal.querySelector('select[name="sexo"]').value = animal.sexo;
    modal.querySelector('input[name="nasc"]').value = animal.nasc;

    const radios = modal.querySelectorAll('input[name="castrado"]');
    radios.forEach(r => {
        r.checked = r.value === String(animal.castrado);
    });

    modal.showModal();
}

//
const formEditarAnimal = document.querySelector("#modal-editar-animal form");

if (formEditarAnimal) {
    formEditarAnimal.addEventListener("submit", function (e) {
        e.preventDefault();

        const dados = {
            nome: formEditarAnimal.nome.value,
            especie: formEditarAnimal.especie.value,
            raca: formEditarAnimal.raca.value,
            cor: formEditarAnimal.cor.value,
            sexo: formEditarAnimal.sexo.value,
            nasc: formEditarAnimal.nasc.value,
            castrado: formEditarAnimal.castrado.value === "true"
        };

        atualizarAnimal(dados);
    });
}

//
async function atualizarAnimal(dados) {
    try {
        const params = new URLSearchParams(window.location.search);
        const tutorId = params.get("id");

        const res = await fetch(`http://localhost:8080/animal/${animalSelecionadoId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dados)
        });

        const atualizado = await res.json();

        document.querySelector("#modal-editar-animal").close();

        listarAnimaisPorTutor(tutorId);

        alert("Animal atualizado com sucesso!");

    } catch (err) {
        console.error("Erro ao atualizar animal:", err);
    }
}


//-------------------------
//----- EXCLUIR ANIMAL ----
//-------------------------
async function excluirAnimal(animalId) {
    const confirmar = confirm("Tem certeza que deseja excluir este animal?");
    if (!confirmar) return;

    try {
        const params = new URLSearchParams(window.location.search);
        const tutorId = params.get("id");

        await fetch(`http://localhost:8080/animal/${animalId}`, {
            method: "DELETE"
        });

        listarAnimaisPorTutor(tutorId);

        alert("Animal excluído com sucesso!");

    } catch (err) {
        console.error("Erro ao excluir animal:", err);
    }
}

//-------------------------
//----- FILTRAR ANIMAL ----
//-------------------------
const inputNomeAnimal = document.querySelector("#input-nome-animal");
const btnBuscarAnimal = document.querySelector("#btn-buscar-animal");

function buscarAnimais() {
    const nomeFiltro = inputNomeAnimal?.value.trim();

    const params = new URLSearchParams(window.location.search);
    const tutorId = params.get("id");

    if (!tutorId) {
        console.error("TutorId não encontrado na URL");
        return;
    }

    if (!nomeFiltro) {
        listarAnimaisPorTutor(tutorId);
        return;
    }

    fetch(`http://localhost:8080/animal/buscar?tutorId=${tutorId}&nome=${encodeURIComponent(nomeFiltro)}`)
        .then(res => res.json())
        .then(animais => {

            const container = document.querySelector(".container-cards-animais");
            if (!container) return;

            container.innerHTML = "";

            if (!animais || animais.length === 0) {
                container.innerHTML = "<p>Nenhum animal encontrado.</p>";
                return;
            }

            animais.forEach(animal => renderCardAnimal(animal));
        })
        .catch(err => console.error("Erro na busca de animais:", err));
}


//----------------------
//------ EVENTOS -------
//----------------------

if (btnBuscarAnimal) {
    btnBuscarAnimal.addEventListener("click", buscarAnimais);
}

if (inputNomeAnimal) {
    inputNomeAnimal.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            buscarAnimais();
        }
    });
}