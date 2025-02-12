package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.curso.*;
import com.alura.foro_hub.domain.curso.dto.DatosActualizarCurso;
import com.alura.foro_hub.domain.curso.dto.DatosListadoCursos;
import com.alura.foro_hub.domain.curso.dto.DatosRegistroCurso;
import com.alura.foro_hub.domain.curso.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRegistroCurso> crearCurso(@RequestBody DatosRegistroCurso datosRegistroCurso) {

        Curso curso = new Curso(datosRegistroCurso);
        cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.OK).body(datosRegistroCurso);

    }

    @GetMapping
    public Page<DatosListadoCursos> listarCursos(@PageableDefault(size = 10) Pageable pageable) {
        Page<Curso> cursos = cursoRepository.findAllByOrderByCategoriaAsc(pageable);
        return cursos.map(DatosListadoCursos::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarCurso(@RequestBody DatosActualizarCurso datosActualizarCurso){
        Optional<Curso> cursoOptional = cursoRepository.findById(datosActualizarCurso.id());
        Curso curso = cursoOptional.get();
        curso.actualizarCurso(datosActualizarCurso);
        return ResponseEntity.status(HttpStatus.OK).body("Registro actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarCursoFisico(@PathVariable Long id) {

        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()){
            cursoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Registro eliminado exitosamente");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }


}
