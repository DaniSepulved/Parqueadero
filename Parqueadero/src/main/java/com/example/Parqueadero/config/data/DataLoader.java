package com.example.Parqueadero.config.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Parqueadero.model.Rol;
import com.example.Parqueadero.model.Usuarios;
import com.example.Parqueadero.repository.UsuarioRepository;

/**
 * Componente encargado de la precarga de datos iniciales en la base de datos
 * al arrancar la aplicación de manera automática.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Inyección de dependencias mediante el constructor.
    */
    public DataLoader(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Método ejecutado inmediatamente después de que inicia la aplicación.
     * Verifica la existencia del administrador y lo crea si no existe.
    */
    @Override
    public void run(String... args) throws Exception {
        // Verifica si ya existe el usuario administrador principal
        if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {
            Usuarios adminUsuarios = Usuarios.builder()
                    .nombre("Admin")
                    .apellido("Principal")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("admin123"))
                    .rol(Rol.ADMIN)
                    .build();

            usuarioRepository.save(adminUsuarios);
            System.out.println("✅ Usuario admin creado");
        } else {
            System.out.println("ℹ Usuario admin ya existe");
        }
    }
}
