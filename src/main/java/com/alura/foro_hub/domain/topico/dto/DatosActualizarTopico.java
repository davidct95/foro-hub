package com.alura.foro_hub.domain.topico.dto;

import com.alura.foro_hub.domain.topico.Estado;

public record DatosActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        Estado estado,
        Long idCurso
        ) {
}
