$(document).ready(function () {
  cargarUsuarios();
  $("#usuarios").DataTable();
  actualizarEmailUsuario();
});

function actualizarEmailUsuario() {
  document.getElementById("txt_email_usuario").outerHTML = localStorage.email;
}

async function cargarUsuarios() {
  const request = await fetch("api/usuarios", {
    method: "GET",
    headers: getHeaders(),
  });
  const usuarios = await request.json();

  let listadoHTML = "";
  for (let usuario of usuarios) {
    let botonEliminar =
      `<a href="#" onclick="eliminarUsuario(${usuario.id})" class="btn btn-danger btn-circle btn-sm">` +
      `<i class="fas fa-trash"></i>` +
      `</a>`;

    let telefono = usuario.telefono ? usuario.telefono : "N/A";
    let usuarioHTML = `<tr>
                          <td>${usuario.id}</td>
                          <td>${usuario.nombre} ${usuario.apellido}</td>
                          <td>${usuario.email}</td>
                          <td>${telefono}</td>
                          <td>
                            ${botonEliminar}
                          </td>
                        </tr>`;
    listadoHTML += usuarioHTML;
  }

  document.querySelector("#usuarios tbody").outerHTML = listadoHTML;
}

async function eliminarUsuario(id) {
  if (confirm("¿Está seguro que desea eliminar el usuario?")) {
    const request = await fetch("api/usuarios/" + id, {
      method: "DELETE",
      headers: getHeaders(),
    });
    location.reload();
  }
}

function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}
