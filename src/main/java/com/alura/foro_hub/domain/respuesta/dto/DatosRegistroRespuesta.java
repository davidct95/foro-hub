package com.alura.foro_hub.domain.respuesta.dto;

public record DatosRegistroRespuesta(
        Long idTopico,
        String mensaje,
        Long idUsuario,
        boolean solucion
) {
}
