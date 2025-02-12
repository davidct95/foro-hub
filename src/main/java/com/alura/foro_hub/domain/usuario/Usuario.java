package com.alura.foro_hub.domain.usuario;

import com.alura.foro_hub.domain.perfil.Perfil;
import com.alura.foro_hub.domain.perfil.TipoPerfil;
import com.alura.foro_hub.domain.respuesta.Respuesta;
import com.alura.foro_hub.domain.topico.Topico;
import com.alura.foro_hub.domain.usuario.dto.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    @OneToMany(mappedBy = "usuario")
    private List<Topico> topicos;
    @ManyToMany
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfiles = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<Respuesta> respuestas;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario, Perfil perfil){
        this.nombre = datosRegistroUsuario.nombre();
        this.correoElectronico = datosRegistroUsuario.correoElectronico();
        this.contrasena = datosRegistroUsuario.contrasena();
        this.perfiles.add(perfil);
    }
}
