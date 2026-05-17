package com.example.Parqueadero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server; 

/**
 * Configuración de OpenAPI / Swagger para la autogeneración 
 * y visualización interactiva de la documentación de la API.
*/
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("API - proyecto Sena - Paqueadero")                      
                .description("SENA - Parqueadero"))
                .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("Servidor Local"));                
    }
}
