package com.alura.foro_hub.domain.topico.dto;

import com.alura.foro_hub.domain.topico.Estado;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        String autor,
        Long idCurso
) {
}
