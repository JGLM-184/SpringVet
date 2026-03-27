const form = document.querySelector("#form-veterinario");

if (form) {
    form.addEventListener("submit", function (e) {
        e.preventDefault();

        if (!form.checkValidity()) {
            form.reportValidity();
            return;
        }

        const dados = {
            nome: form.nome.value.trim(),
            especialidade: form.especialidade.value,
            crmv: form.crmv.value.trim(),
            telefone: form.telefone.value.trim(),
            email: form.email.value.trim(),
            ativo: true
        };

        criarVeterinario(dados);
    });
}

function criarVeterinario(dados) {

    console.log(dados);

    fetch("http://localhost:8080/veterinario", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    })
        .then(async res => {
            if (!res.ok) {
                const erro = await res.text();
                console.error("Erro do backend:", erro);
                throw new Error("Erro ao cadastrar veterinário");
            }
            return res.json();
        })
        .then(() => {

            alert("Veterinário cadastrado com sucesso!");

            window.location.href = "/pages/veterinarios.html";
        })
        .catch(err => {
            console.error(err);
            alert("Erro ao cadastrar veterinário");
        });
}