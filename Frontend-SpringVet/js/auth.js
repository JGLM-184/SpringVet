function getToken() {
    return localStorage.getItem("token");
}

function verificarLogin() {
    if (!getToken()) {
        window.location.href = "../index.html";
    }
}

function logout() {
    const confirmar = confirm("Deseja sair?");

    if (confirmar) {
        localStorage.removeItem("token");
        window.location.href = "../index.html";
    }
}

async function fetchComToken(url, options = {}) {
    const token = getToken();

    const response = await fetch(url, {
        ...options,
        headers: {
            "Authorization": "Bearer " + token,
            ...(options.headers || {})
        }
    });

    if (response.status === 401) {
        logout();
    }

    return response;
}