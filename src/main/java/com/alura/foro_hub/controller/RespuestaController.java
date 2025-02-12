package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.respuesta.*;
import com.alura.foro_hub.domain.respuesta.dto.DatosActualizarRespuesta;
import com.alura.foro_hub.domain.respuesta.dto.DatosListadoRespuestas;
import com.alura.foro_hub.domain.respuesta.dto.DatosRegistroRespuesta;
import com.alura.foro_hub.domain.respuesta.repository.RespuestaRepository;
import com.alura.foro_hub.domain.topico.Topico;
import com.alura.foro_hub.domain.topico.repository.TopicoRepository;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    RespuestaRepository respuestaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity crearRespuesta(@RequestBody DatosRegistroRespuesta datosRegistroRespuesta){
        Optional<Topico> topicoOptional = topicoRepository.findById(datosRegistroRespuesta.idTopico());
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(datosRegistroRespuesta.idUsuario());

        if (topicoOptional.isPresent() && usuarioOptional.isPresent()) {
            Respuesta respuesta = new Respuesta(datosRegistroRespuesta, topicoOptional.get(), usuarioOptional.get());
            respuestaRepository.save(respuesta);
            return ResponseEntity.status(HttpStatus.CREATED).body(datosRegistroRespuesta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el tópico o usuario ingresado");
        }
    }


    @GetMapping("/{idTopico}")
    public ResponseEntity mostrarRespuestasDeUnTopico(@PathVariable Long idTopico){
        List<Respuesta> respuestas = respuestaRepository.findAllByTopicoId(idTopico);

        if (respuestas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<DatosListadoRespuestas> datosListadoRespuestas = respuestas.stream().map(DatosListadoRespuestas::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(datosListadoRespuestas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosActualizarRespuesta> actualizarRespuesta(@PathVariable Long id, @RequestBody DatosActualizarRespuesta datosActualizarRespuesta) {
        Optional<Respuesta> respuestaOptional = respuestaRepository.findById(id);

        if (respuestaOptional.isPresent()) {
            Respuesta respuesta = respuestaOptional.get();
            respuesta.actualizarDatos(datosActualizarRespuesta);
            return ResponseEntity.status(HttpStatus.OK).body(datosActualizarRespuesta);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRegistro(@PathVariable Long id){
        Optional<Respuesta> respuestaOptional = respuestaRepository.findById(id);
        if (respuestaOptional.isPresent()) {
            Respuesta respuesta = respuestaOptional.get();
            respuesta.eliminarRegistroLogico();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
