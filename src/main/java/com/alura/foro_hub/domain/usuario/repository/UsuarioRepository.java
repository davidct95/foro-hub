package com.alura.foro_hub.domain.usuario.repository;

import com.alura.foro_hub.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

    UserDetails findByNombre(String username);
}
