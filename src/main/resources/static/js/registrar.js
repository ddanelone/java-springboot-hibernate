$(document).ready(function () {
  // on ready
});

async function registrarUsuarios() {
  let datos = {};
  datos.nombre = document.getElementById("txt_nombre").value;
  datos.apellido = document.getElementById("txt_apellido").value;
  datos.email = document.getElementById("txt_email").value;
  datos.password = document.getElementById("txt_password").value;
  //datos.telefono = document.getElementById("telefono").value;

  let repeat_password = document.getElementById("txt_repeat_password").value;

  if (datos.password != repeat_password) {
    alert("Las contraseñas no coinciden");
    return;
  }

  const request = await fetch("api/usuarios", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });
  alert("La cuenta fue creada con éxito.");
  window.location.href = "login.html";
}
