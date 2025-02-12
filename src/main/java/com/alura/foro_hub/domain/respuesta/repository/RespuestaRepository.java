package com.alura.foro_hub.domain.respuesta.repository;

import com.alura.foro_hub.domain.respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findAllByTopicoId(Long idTopico);
}
