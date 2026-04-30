verificarLogin();

const form = document.querySelector(".form-grid");

if (!form) {
    console.error("Form não encontrado!");
}

//----------------------
// SUBMIT DO FORM
//----------------------
form.addEventListener("submit", function (e) {
    e.preventDefault();

    const tutor = {
        nome: form.nome.value,
        cpf: form.cpf.value,
        email: form.email.value,
        telefone: form.telefone.value,
        animais: []
    };

    const animaisForms = document.querySelectorAll(".animal-form");

    animaisForms.forEach(formAnimal => {
        const animal = {
            nome: formAnimal.querySelector('[name*="nome"]').value,
            especie: formAnimal.querySelector('[name*="especie"]').value,
            raca: formAnimal.querySelector('[name*="raca"]').value,
            cor: formAnimal.querySelector('[name*="cor"]').value,
            sexo: formAnimal.querySelector('[name*="sexo"]').value,
            nasc: formAnimal.querySelector('[name*="nasc"]').value,
            castrado: formAnimal.querySelector('[name*="castrado"]:checked')?.value === "true"
        };

        tutor.animais.push(animal);
    });

    fetchComToken("http://localhost:8080/tutor", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(tutor)
    })
        .then(res => res.json())
        .then(data => {
            alert("Tutor cadastrado com sucesso!");
            window.location.href = "/pages/tutores.html";
        })
        .catch(err => console.error("Erro:", err));
});