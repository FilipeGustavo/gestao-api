package br.com.gestao.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GestaoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoApiApplication.class, args);
    }
}
