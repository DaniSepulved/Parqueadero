package com.example.Parqueadero.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Parqueadero.security.JwtFilter;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Configuración centralizada de la seguridad de la aplicación (Spring Security).
 * Gestiona filtros, permisos de rutas, estados de sesión y encriptación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Define las reglas de acceso por URL, control de excepciones y añade el filtro JWT.
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas accesibles sin autenticación
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Rutas protegidas y restringidas por Roles específicos
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/empleado/**").hasRole("EMPLEADO")
                        .requestMatchers("/api/cliente/**").hasRole("CLIENTE")
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex

                        // Manejo de error cuando no se envía un token válido
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Not authenticated\"}\n");
                        })

                        // Manejo de error cuando el rol no tiene permisos suficientes
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Access denied\"}\n");
                        }))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Bean para el hashing de contraseñas utilizando el algoritmo BCrypt.
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}