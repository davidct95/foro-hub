package com.alura.foro_hub.domain.topico.dto;

import com.alura.foro_hub.domain.topico.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "El título es obligatorio")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "El estado no puede ser null")
        Estado estado,
        @NotBlank(message = "El nombre del autor es obligatorio")
        String autor,
        @NotNull
        Long idCurso,
        @NotNull
        Long idUsuario
) {}
