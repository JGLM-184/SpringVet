const form = document.getElementById("form-login");

form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("exampleInputEmail1").value;
    const senha = document.getElementById("exampleInputPassword1").value;

    try {
        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                senha: senha
            })
        });

        if (!response.ok) {
            alert("Email ou senha inválidos");
            return;
        }

        const data = await response.json();

        localStorage.setItem("token", data.token);

        window.location.href = "/pages/home.html";

    } catch (error) {
        alert("Erro ao conectar com servidor");
        console.error(error);
    }
});