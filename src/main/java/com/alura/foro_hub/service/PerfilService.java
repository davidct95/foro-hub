package com.alura.foro_hub.service;

import com.alura.foro_hub.domain.perfil.DatosRegistroPerfil;
import com.alura.foro_hub.domain.perfil.Perfil;
import com.alura.foro_hub.domain.perfil.PerfilRepository;
import com.alura.foro_hub.domain.perfil.TipoPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil obtenerOCrearPerfil(TipoPerfil tipoPerfil) {
        Optional<Perfil> perfilOptional = perfilRepository.findByNombre(tipoPerfil);
        if (perfilOptional.isPresent()){
            return perfilOptional.get();
        } else {
            DatosRegistroPerfil datosRegistroPerfil = new DatosRegistroPerfil(tipoPerfil);
            Perfil perfil = new Perfil(datosRegistroPerfil);
            perfilRepository.save(perfil);
            return perfil;
        }
    }

}
