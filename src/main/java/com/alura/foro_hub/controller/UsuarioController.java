package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.perfil.Perfil;
import com.alura.foro_hub.domain.perfil.TipoPerfil;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.dto.DatosRegistroUsuario;
import com.alura.foro_hub.domain.usuario.repository.UsuarioRepository;
import com.alura.foro_hub.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilService perfilService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        Perfil perfil = perfilService.obtenerOCrearPerfil(TipoPerfil.USUARIO);
        Usuario usuario = new Usuario(datosRegistroUsuario, perfil, passwordEncoder);
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario creado Correctamente");
    }

}
