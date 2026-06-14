package com.example.Parqueadero.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Parqueadero.dto.UsuarioLoginDTO;
import com.example.Parqueadero.exception.AuthenticationException;
import com.example.Parqueadero.model.Usuarios;
import com.example.Parqueadero.security.JwtUtil;
import com.example.Parqueadero.services.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST encargado de gestionar los procesos de autenticación del sistema.
*/
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    /**
     * Endpoint para iniciar sesión y obtener el token JWT.
     * UsuarioLoginDTO con las credenciales del usuario (email y password).
     */
@PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(@RequestBody UsuarioLoginDTO loginUser) {
    try {
        Usuarios usuario = new Usuarios(loginUser.getEmail(), loginUser.getPassword());
        Usuarios usuarioAutenticado = authService.login(usuario);

        String token = jwtUtil.generateToken(usuarioAutenticado);
        
        // Creamos el mapa explícitamente como <String, Object>
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("token", token);
        respuesta.put("id", usuarioAutenticado.getIdUsuario());
        respuesta.put("email", usuarioAutenticado.getEmail());
        respuesta.put("nombre", usuarioAutenticado.getNombre());
        respuesta.put("apellido", usuarioAutenticado.getApellido());
        respuesta.put("role", usuarioAutenticado.getRol() != null ? usuarioAutenticado.getRol().name() : "EMPLEADO");
        
        return ResponseEntity.ok(respuesta);

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body(Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Error interno del servidor"));
        }
    }
}