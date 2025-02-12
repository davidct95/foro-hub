package com.alura.foro_hub.service;

import com.alura.foro_hub.domain.perfil.Perfil;
import com.alura.foro_hub.domain.perfil.TipoPerfil;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleManagementService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilService perfilService;

    @Transactional
    public Usuario agregarRolAUsuario(Long idUsuario, TipoPerfil tipoPerfil){
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Perfil perfil = perfilService.obtenerOCrearPerfil(tipoPerfil);
        usuario.getPerfiles().add(perfil);
        return usuarioRepository.save(usuario);
    }

    public Usuario eliminarRol(Long idUsuario, TipoPerfil tipoPerfil){
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Perfil perfil = perfilService.obtenerOCrearPerfil(tipoPerfil);
        if(usuario.getPerfiles().contains(perfil)) {
            usuario.getPerfiles().remove(perfil);
        } else {
            throw new RuntimeException("El usuario no tiene el rol específico");
        }
        return usuarioRepository.save(usuario);
    }

}
