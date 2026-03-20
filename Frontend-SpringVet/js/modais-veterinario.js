const modalVeterinario = document.getElementById("modal-editar-veterinario");
const btnEditarVeterinario = document.getElementById("btn-editar-veterinario");
const btnCancelarVeterinario = document.getElementById("cancelar-editar-veterinario");

btnEditarVeterinario.addEventListener("click", () => {
    modalVeterinario.showModal();
});

btnCancelarVeterinario.addEventListener("click", () => {
    modalVeterinario.close();
});