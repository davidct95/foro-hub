package com.alura.foro_hub.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank(message = "El campo no puede estar vacío")
        String nombre,
        @NotBlank(message = "La clave no puede estar vacía")
        String clave) {
}
