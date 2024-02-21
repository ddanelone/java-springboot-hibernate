package com.cursoJava.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dao")
@EntityScan(basePackages = "models")

public class CursoApplication {

   public static void main(String[] args) {
      SpringApplication.run(CursoApplication.class, args);
   }

}