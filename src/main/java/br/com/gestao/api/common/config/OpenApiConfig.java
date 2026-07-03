package br.com.gestao.api.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestaoOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestao API")
                        .version("v1")
                        .description("API REST para cadastro de clientes, produtos e futuros modulos de gestao."));
    }
}
