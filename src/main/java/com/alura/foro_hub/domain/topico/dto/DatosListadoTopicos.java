package com.alura.foro_hub.domain.topico.dto;

import com.alura.foro_hub.domain.topico.Estado;
import com.alura.foro_hub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado estado,
        String usuario,
        String curso
) {
    public DatosListadoTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getEstado(), topico.getUsuario().getNombre(),
                topico.getCurso().getNombre());
    }
}
