package com.alura.foro_hub.domain.curso;

import com.alura.foro_hub.domain.curso.dto.DatosActualizarCurso;
import com.alura.foro_hub.domain.curso.dto.DatosRegistroCurso;
import com.alura.foro_hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cursos")
@Entity(name = "Curso")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    @Enumerated(EnumType.STRING)
    Categoria categoria;
    @OneToMany(mappedBy = "curso")
    List<Topico> topicos;

    public Curso(DatosRegistroCurso datosRegistroCurso){
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoria();
    }

    public void actualizarCurso(DatosActualizarCurso datosActualizarCurso){
        this.nombre = datosActualizarCurso.nombre();
        this.categoria = datosActualizarCurso.categoria();
    }

}
