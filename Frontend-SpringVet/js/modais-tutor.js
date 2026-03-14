const modalTutor = document.getElementById("modal-editar-tutor");
const btnEditarTutor = document.getElementById("btn-editar-tutor");
const btnCancelarTutor = document.getElementById("cancelar-editar-tutor");

btnEditarTutor.addEventListener("click", () => {
    modalTutor.showModal();
});

btnCancelarTutor.addEventListener("click", () => {
    modalTutor.close();
});


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