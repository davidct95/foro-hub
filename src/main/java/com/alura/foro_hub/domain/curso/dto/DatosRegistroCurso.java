package com.alura.foro_hub.domain.curso.dto;

import com.alura.foro_hub.domain.curso.Categoria;

public record DatosRegistroCurso(
        String nombre,
        Categoria categoria
) {}
