package com.alura.foro_hub.domain.usuario.repository;

import com.alura.foro_hub.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
