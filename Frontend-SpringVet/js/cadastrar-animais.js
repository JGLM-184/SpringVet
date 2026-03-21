let contador = 1;

function adicionarAnimal() {

    const lista = document.getElementById("cadastrar-animais");

    const novoAnimal = document.createElement("div");

    novoAnimal.innerHTML = `
    <div class="flex-box-animal">
        <input class="input-text" type="text" name="animais[${contador}].nome" placeholder="Nome">

        <select class="input-text" name="animais[${contador}].especie">
            <option value="">Espécie</option>
            <option>Cachorro</option>
            <option>Gato</option>
        </select>

        <input class="input-text" type="text" name="animais[${contador}].raca" placeholder="Raça">

        <input class="input-text" type="text" name="animais[${contador}].cor" placeholder="Cor">

        <select class="input-text" name="animais[${contador}].sexo">
            <option value="">Sexo</option>
            <option>Macho</option>
            <option>Fêmea</option>
        </select>

        <input class="input-text" type="date" name="animais[${contador}].nasc" placeholder="Data de nascimento">

        <label>Castrado?</label>
        <div class="group-radio">
            <div>
                <input class="radio-btn" type="radio" name="animais[${contador}].castrado" value="true">
                <label>Sim</label>
            </div>

            <div>
                <input class="radio-btn" type="radio" name="animais[${contador}].castrado" value="false">
                <label>Não</label>
            </div>
        </div>
        <div>
            <button type="button" class="btn btn-danger" onclick="this.closest('.flex-box-animal').remove()">
                <span class="material-symbols-outlined"> delete </span>
            </button>
        </div>
    </div>
    `;

    lista.appendChild(novoAnimal);

    contador++;
}