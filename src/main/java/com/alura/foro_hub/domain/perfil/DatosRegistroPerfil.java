package com.alura.foro_hub.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroPerfil(
        @NotBlank(message = "Se requiere un nombre")
        TipoPerfil nombre
) {
}
