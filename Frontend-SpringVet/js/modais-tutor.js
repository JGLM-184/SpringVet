const modalTutor = document.getElementById("modal-editar-tutor");
const btnEditarTutor = document.getElementById("btn-editar-tutor");
const btnCancelarTutor = document.getElementById("cancelar-editar-tutor");

btnEditarTutor.addEventListener("click", () => {
    modalTutor.showModal();
});

btnCancelarTutor.addEventListener("click", () => {
    modalTutor.close();
});

/* CRIAR ANIMAL */

const modalAnimal = document.getElementById("modal-editar-animal");
const btnCancelarAnimal = document.getElementById("cancelar-editar-animal");

document.addEventListener("click", function(e) {

    if (e.target.closest(".btn-editar-animal")) {
        modalAnimal.showModal();
    }

});

btnCancelarAnimal.addEventListener("click", () => {
    modalAnimal.close();
});

/* CRIAR ANIMAL */

const modalNovoAnimal = document.getElementById("modal-novo-animal");
const btnNovoAnimal = document.getElementById("btn-novo-animal");
const btnCancelarNovoAnimal = document.getElementById("cancelar-novo-animal");

btnNovoAnimal.addEventListener("click", () => {
    modalNovoAnimal.showModal();
});

btnCancelarNovoAnimal.addEventListener("click", () => {
    modalNovoAnimal.close();
});

/* CRIAR CONSULTA */

const modalConsulta = document.getElementById("modal-nova-consulta");
const btnConsulta = document.getElementById("btn-nova-consulta");
const btnCancelarConsulta = document.getElementById("cancelar-nova-consulta");

btnConsulta.addEventListener("click", () => {
    modalConsulta.showModal();
});

btnCancelarConsulta.addEventListener("click", () => {
    modalConsulta.close();
});