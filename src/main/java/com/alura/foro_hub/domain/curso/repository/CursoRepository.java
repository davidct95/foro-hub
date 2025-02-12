package com.alura.foro_hub.domain.curso.repository;

import com.alura.foro_hub.domain.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Page<Curso> findAllByOrderByCategoriaAsc(Pageable pageable);

}
