package com.cursoJava.curso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.UsuarioDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import models.Usuario;
import utils.JWTUtil;

@RestController
public class UsuarioController {

   @Autowired
   private UsuarioDao usuarioDao;

   @Autowired
   private JWTUtil jwtUtil;

   private boolean validarToken(String token) {
      String usuario_id = jwtUtil.getKey(token);
      return usuario_id != null;
   }

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
   public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {
      if (!validarToken(token)) {
         return new ArrayList<>();
      }
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
   public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
      if (!validarToken(token)) {
         return;
      }
      usuarioDao.eliminar(id);
   }

   @RequestMapping(value = "api/login", method = RequestMethod.POST)
   public String login(@RequestBody Usuario usuario) {

      Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
      if (usuarioLogueado != null) {
         String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
         return tokenJwt;
      }
      return "Error";
   }

}
