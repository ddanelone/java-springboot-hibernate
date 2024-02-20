package com.cursoJava.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.UsuarioDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import models.Usuario;

@RestController
public class UsuarioController {

   @Autowired
   private UsuarioDao usuarioDao;

   @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
   public Usuario getUsuario(@PathVariable Long id) {
      Usuario usuario = new Usuario();
      usuario.setId(id);
      usuario.setNombre("Juan");
      usuario.setApellido("Perez");
      usuario.setEmail("ddanelone@hotmail.com");
      usuario.setTelefono("1234567890");

      return usuario;
   }

   @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
   public List<Usuario> getUsuarios() {
      return usuarioDao.getUsuarios();
   }

   @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
   public void RegistrarUsuario(@RequestBody Usuario usuario) {
      Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
      String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
      usuario.setPassword(hash);

      usuarioDao.registrar(usuario);
   }

   @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
   public void eliminar(@PathVariable Long id) {
      usuarioDao.eliminar(id);
   }

   @RequestMapping(value = "api/login", method = RequestMethod.POST)
   public String login(@RequestBody Usuario usuario) {
      if (usuarioDao.verificarCredenciales(usuario)) {
         return "Ok";
      } else {
      }
      return "Error";
   }

}
