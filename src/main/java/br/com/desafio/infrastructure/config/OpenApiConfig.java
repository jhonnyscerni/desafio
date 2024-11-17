package br.com.desafio.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio API")
                        .version("1.0")
                        .description("API para gerenciamento de compras e recomendações de vinhos. Inclui endpoints para listar compras, obter a maior compra por ano e obter recomendações de vinhos com base no CPF do cliente. Utiliza Spring Boot e está documentada com OpenAPI 3."))
                .addServersItem(new Server().url("http://localhost:8080").description("Development server"));
    }
}