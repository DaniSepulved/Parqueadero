package com.example.Parqueadero.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Parqueadero.model.Usuarios;
import com.example.Parqueadero.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Filtro de seguridad que intercepta las solicitudes HTTP para validar el token JWT presente en el encabezado de autorización.
@Component
public class JwtFilter extends OncePerRequestFilter {

    // Inyección de dependencias para el componente JwtUtil y el repositorio de usuarios.
    @Autowired
    private JwtUtil jwtUtil;

    // Inyección del repositorio de usuarios para acceder a la información del usuario durante la validación del token.
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método que se ejecuta para cada solicitud HTTP, validando el token JWT y estableciendo la 
    // autenticación en el contexto de seguridad si el token es válido.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.getEmailFromToken(token); // asegúrate de que el método esté bien

                Optional<Usuarios> optionalUser = usuarioRepository.findByEmail(email);
                if (optionalUser.isPresent()) {
                    Usuarios usuarios = optionalUser.get();

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    usuarios,
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuarios.getRol()))
                            );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
