package com.alura.foro_hub.domain.curso.dto;

import com.alura.foro_hub.domain.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,
        @NotBlank
        String nombre,
        @NotBlank
        Categoria categoria
) {
}
