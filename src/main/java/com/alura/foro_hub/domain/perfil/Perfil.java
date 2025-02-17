package com.alura.foro_hub.domain.perfil;

import com.alura.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    TipoPerfil nombre;
    @ManyToMany(mappedBy = "perfiles", fetch = FetchType.EAGER)
    List<Usuario> usuarios;

    public Perfil(DatosRegistroPerfil datosRegistroPerfil) {
        this.nombre = datosRegistroPerfil.nombre();
    }

}
