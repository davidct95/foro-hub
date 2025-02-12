package com.alura.foro_hub.domain.curso.dto;

import com.alura.foro_hub.domain.curso.Categoria;
import com.alura.foro_hub.domain.curso.Curso;

public record DatosListadoCursos(
        String nombre,
        Categoria categoria
) {
    public DatosListadoCursos(Curso curso){
        this(curso.getNombre(), curso.getCategoria());
    }
}
