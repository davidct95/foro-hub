package com.alura.foro_hub.domain.topico.repository;

import com.alura.foro_hub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findAllByOrderByFechaCreacionAsc(Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso.id = :idCurso AND YEAR(t.fechaCreacion) = :anio")
    List<Topico> buscarPorCursoYAnio (@Param("idCurso") Long idCurso, @Param("anio") int anio);

}
