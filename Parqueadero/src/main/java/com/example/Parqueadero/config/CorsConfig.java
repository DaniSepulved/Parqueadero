package com.example.Parqueadero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de CORS (Cross-Origin Resource Sharing) para permitir
 * que clientes externos (Frontend, Mobile) consuman la API sin bloqueos de seguridad.
 */
@Configuration
public class CorsConfig {
     @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite CORS en todos los endpoints
                        .allowedOrigins("https://b2pi2-876585927226.us-central1.run.app", "http://localhost:3000", "http://localhost:5173", "http://localhost:5175", "exp://192.168.1.4:8081", "http://localhost:8081", "http://192.168.1.4:8081") // Origen permitido
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowedHeaders("*") // Todos los encabezados permitidos
                        .allowCredentials(true); // Si necesitas enviar cookies o autenticación 
            }
        };
    }
}
