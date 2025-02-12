package com.alura.foro_hub.domain.respuesta.dto;

import com.alura.foro_hub.domain.respuesta.Respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuestas(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        boolean solucion,
        String usuarioNombre
) {
    public DatosListadoRespuestas(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.isSolucion(), respuesta.getUsuario().getNombre());
    }
}
