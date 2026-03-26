const API_URL = "http://localhost:8088";

// REGISTER
function register() {
    const username = document.getElementById("regUsername").value;
    const password = document.getElementById("regPassword").value;

    fetch(`${API_URL}/auth/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(res => res.text())
    .then(() => {
        alert("Registered successfully");
        window.location.href = "index.html";
    });
}

// LOGIN
function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch(`${API_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(res => res.json())
    .then(data => {
        localStorage.setItem("token", data.token);
        window.location.href = "dashboard.html";
    });
}

// ADD TASK
function addTask() {
    const task = document.getElementById("task").value;
    const token = localStorage.getItem("token");

    fetch(`${API_URL}/todos`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({ title: task })
    })
    .then(() => {
        document.getElementById("task").value = "";
        loadTasks();
    });
}

// LOAD TASKS
function loadTasks() {
    const token = localStorage.getItem("token");

    fetch(`${API_URL}/todos`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(data => {
        const list = document.getElementById("taskList");
        list.innerHTML = "";

        data.forEach(todo => {
            const li = document.createElement("li");

            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.checked = todo.completed;

            checkbox.onclick = () => toggleTask(todo.id);

            const text = document.createElement("span");
            text.innerText = " " + todo.title;

            if (todo.completed) {
                text.style.textDecoration = "line-through";
            }

            li.appendChild(checkbox);
            li.appendChild(text);

            list.appendChild(li);
        });
    });
}

// TOGGLE TASK
function toggleTask(id) {
    const token = localStorage.getItem("token");

    fetch(`${API_URL}/todos/${id}/toggle`, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(() => loadTasks());
}

// LOGOUT
function logout() {
    localStorage.removeItem("token");
    window.location.href = "index.html";
}

// AUTO LOAD
if (window.location.pathname.includes("dashboard.html")) {
    loadTasks();
}