const modalConsulta = document.getElementById("modal-editar-consulta");
const btnEditarConsulta = document.getElementById("btn-editar-consulta");
const btnCancelarConsulta = document.getElementById("cancelar-editar-consulta");

btnEditarConsulta.addEventListener("click", () => {
    modalConsulta.showModal();
});

btnCancelarConsulta.addEventListener("click", () => {
    modalConsulta.close();
});