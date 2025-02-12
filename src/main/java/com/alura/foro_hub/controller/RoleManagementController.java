package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.perfil.Perfil;
import com.alura.foro_hub.domain.perfil.TipoPerfil;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.repository.UsuarioRepository;
import com.alura.foro_hub.service.PerfilService;
import com.alura.foro_hub.service.RoleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gestion-roles")
public class RoleManagementController {

    @Autowired
    RoleManagementService roleManagementService;

    @PostMapping("/agregar-rol/{idUsuario}")
    public ResponseEntity<String> agregarRol(@PathVariable Long idUsuario, @RequestParam TipoPerfil tipoPerfil){
        try {
            Usuario usuario = roleManagementService.agregarRolAUsuario(idUsuario, tipoPerfil);
            return ResponseEntity.status(HttpStatus.OK).body("Rol de " + tipoPerfil.name() + " agregado correctamente al usuario " + usuario.getNombre());
        } catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar-rol/{idUsuario}")
    public ResponseEntity<String> eliminarRol(@PathVariable Long idUsuario, @RequestParam TipoPerfil tipoPerfil){
        try {
            Usuario usuario = roleManagementService.eliminarRol(idUsuario, tipoPerfil);
            return ResponseEntity.status(HttpStatus.OK).body("Rol de " + tipoPerfil.name() + " eliminado correctamente del usuario " + usuario.getNombre());
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
