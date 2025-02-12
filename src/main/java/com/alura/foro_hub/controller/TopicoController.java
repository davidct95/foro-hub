package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.curso.Curso;
import com.alura.foro_hub.domain.curso.repository.CursoRepository;
import com.alura.foro_hub.domain.topico.*;
import com.alura.foro_hub.domain.topico.dto.DatosActualizarTopico;
import com.alura.foro_hub.domain.topico.dto.DatosListadoTopicos;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro_hub.domain.topico.repository.TopicoRepository;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRegistroTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        Optional<Curso> cursoOptional = cursoRepository.findById(datosRegistroTopico.idCurso());
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(datosRegistroTopico.idUsuario());

        if (cursoOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Curso curso = cursoOptional.get();
            Topico topico = new Topico(datosRegistroTopico, curso, usuario);
            topicoRepository.save(topico);
            return ResponseEntity.status(HttpStatus.CREATED).body(datosRegistroTopico);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping
    public Page<DatosListadoTopicos> obtenerListaTopicos(@PageableDefault(size = 10) Pageable pageable) {
        Page<Topico> topicosPage = topicoRepository.findAllByOrderByFechaCreacionAsc(pageable);
        return topicosPage.map(DatosListadoTopicos::new);
    }

    @GetMapping("buscar")
    public ResponseEntity<List<DatosListadoTopicos>> buscarPorCursoYAnio(@RequestParam Long idCurso, @RequestParam int anio) {
        List<Topico> topicos = topicoRepository.buscarPorCursoYAnio(idCurso, anio);
        if(topicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<DatosListadoTopicos> datosListadoTopicos = topicos.stream().map(DatosListadoTopicos::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(datosListadoTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopicos> buscarPorId(@PathVariable Long id){
        Optional<Topico> optionaltopico = topicoRepository.findById(id);

        if (optionaltopico.isPresent()){
            Topico topico = optionaltopico.get();
            DatosListadoTopicos datosListadoTopicos = new DatosListadoTopicos(topico);
            return ResponseEntity.status(HttpStatus.OK).body(datosListadoTopicos);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody DatosActualizarTopico datosActualizarTopico){
        Optional<Topico> topicoOpt = topicoRepository.findById(datosActualizarTopico.id());
        Optional<Curso> cursoOptional = cursoRepository.findById(datosActualizarTopico.idCurso());

        if (topicoOpt.isPresent()){
            Curso curso = cursoOptional.get();
            Topico topico = topicoOpt.get();
            topico.actualizarDatos(datosActualizarTopico, curso);
            return  ResponseEntity.ok(new DatosListadoTopicos(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                    topico.getFechaCreacion(), topico.getEstado(),topico.getUsuario().getNombre(), topico.getCurso().getNombre()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el registro con el id seleccionado");
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRegistro(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.eliminarRegistroLogicamente();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Registro eliminado satisfactoriamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el registro con el id seleccionado");
        }
    }
}
