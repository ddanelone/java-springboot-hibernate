package com.cursoJava.curso;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import dao.UsuarioDao;
import dao.UsuarioDaoImp;

@Configuration
@ComponentScan(basePackages = { "utils" })
public class AppConfig {

   @Bean
   public UsuarioDao usuarioDao() {
      return new UsuarioDaoImp();
   }

}
