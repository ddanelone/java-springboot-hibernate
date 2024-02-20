$(document).ready(function () {
  // on ready
});

async function iniciarSesion() {
  let datos = {};
  datos.email = document.getElementById("txt_email").value;
  datos.password = document.getElementById("txt_password").value;

  const request = await fetch("api/login", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });

  const respuesta = await request.text();

  if (respuesta == "Ok") {
    window.location.href = "usuarios.html";
  } else {
    alert("Usuario o contraseña incorrectos");
  }
}
