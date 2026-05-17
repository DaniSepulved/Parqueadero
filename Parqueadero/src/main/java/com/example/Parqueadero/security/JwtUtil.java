package com.example.Parqueadero.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.Parqueadero.model.Usuarios;

import java.util.Date;

// Componente para manejar la generación y validación de tokens JWT.
@Component
public class JwtUtil {

    // Clave secreta para firmar los tokens JWT, inyectada desde el archivo de configuración (application.properties).
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // Tiempo de expiración del token en milisegundos, inyectado desde el archivo de configuración (application.properties).
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    // Método para generar un token JWT a partir del email y rol del usuario.
    public String generateToken(String email, String rol) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Sobrecarga del método para generar un token JWT a partir de un objeto Usuarios.
    public String generateToken(Usuarios usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("role", usuario.getRol().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Método para extraer los claims (reclamaciones) de un token JWT.
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para obtener el email del usuario a partir del token JWT.
    public String getEmailFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    // Método para obtener el rol del usuario a partir del token JWT.
    public String getRoleFromToken(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // Método para validar un token JWT, verificando su firma y fecha de expiración.
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}