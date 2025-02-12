package com.alura.foro_hub.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @Email
        @NotBlank
        String correoElectronico,
        @NotBlank
        String contrasena
) {
}
