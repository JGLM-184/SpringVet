document.addEventListener("DOMContentLoaded", () => {

    // =========================
    // EDITAR TUTOR
    // =========================
    const modalTutor = document.getElementById("modal-editar-tutor");
    const btnEditarTutor = document.getElementById("btn-editar-tutor");
    const btnCancelarTutor = document.getElementById("cancelar-editar-tutor");

    if (btnEditarTutor && modalTutor) {
        btnEditarTutor.addEventListener("click", () => {

            const nome = document.querySelector("#nome").textContent;
            const cpf = document.querySelector("#cpf").textContent;
            const email = document.querySelector("#email").textContent;
            const telefone = document.querySelector("#telefone").textContent;

            modalTutor.querySelector('input[name="nome"]').value = nome;
            modalTutor.querySelector('input[name="cpf"]').value = cpf;
            modalTutor.querySelector('input[name="email"]').value = email;
            modalTutor.querySelector('input[name="telefone"]').value = telefone;

            modalTutor.showModal();
        });
    }

    if (btnCancelarTutor && modalTutor) {
        btnCancelarTutor.addEventListener("click", () => {
            modalTutor.close();
        });
    }


    // =========================
    // EDITAR ANIMAL
    // =========================
    const modalAnimal = document.getElementById("modal-editar-animal");
    const btnCancelarAnimal = document.getElementById("cancelar-editar-animal");

    if (btnCancelarAnimal && modalAnimal) {
        btnCancelarAnimal.addEventListener("click", () => {
            modalAnimal.close();
        });
    }


    // =========================
    // NOVO ANIMAL
    // =========================
    const modalNovoAnimal = document.getElementById("modal-novo-animal");
    const btnNovoAnimal = document.getElementById("btn-novo-animal");
    const btnCancelarNovoAnimal = document.getElementById("cancelar-novo-animal");

    if (btnNovoAnimal && modalNovoAnimal) {
        btnNovoAnimal.addEventListener("click", () => {
            modalNovoAnimal.showModal();
        });
    }

    if (btnCancelarNovoAnimal && modalNovoAnimal) {
        btnCancelarNovoAnimal.addEventListener("click", () => {
            modalNovoAnimal.close();
        });
    }


    // =========================
    // EDITAR VETERINÁRIO
    // =========================
    const modalVeterinario = document.getElementById("modal-editar-veterinario");
    const btnEditarVeterinario = document.getElementById("btn-editar-veterinario");
    const btnCancelarVeterinario = document.getElementById("cancelar-editar-veterinario");

    if (btnEditarVeterinario && modalVeterinario) {
        btnEditarVeterinario.addEventListener("click", () => {
            modalVeterinario.showModal();
        });
    }

    if (btnCancelarVeterinario && modalVeterinario) {
        btnCancelarVeterinario.addEventListener("click", () => {
            modalVeterinario.close();
        });
    }

    // =========================
    // NOVA CONSULTA
    // =========================
    const modalNovaConsulta = document.getElementById("modal-nova-consulta");
    const btnCancelarNovaConsulta = document.getElementById("cancelar-nova-consulta");

    if (btnCancelarNovaConsulta && modalNovaConsulta) {
        btnCancelarNovaConsulta.addEventListener("click", () => {
            modalNovaConsulta.close();
        });
    }


    // =========================
    // EDITAR CONSULTA
    // =========================
    const modalConsulta = document.getElementById("modal-editar-consulta");
    const btnEditarConsulta = document.getElementById("btn-editar-consulta");
    const btnCancelarConsulta = document.getElementById("cancelar-editar-consulta");

    if (btnEditarConsulta && modalConsulta) {
        btnEditarConsulta.addEventListener("click", () => {
            modalConsulta.showModal();
        });
    }

    if (btnCancelarConsulta && modalConsulta) {
        btnCancelarConsulta.addEventListener("click", () => {
            modalConsulta.close();
        });
    }

});