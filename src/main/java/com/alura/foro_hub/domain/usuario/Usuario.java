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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    @OneToMany(mappedBy = "usuario")
    private List<Topico> topicos;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfiles = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<Respuesta> respuestas;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario, Perfil perfil, PasswordEncoder passwordEncoder){
        this.nombre = datosRegistroUsuario.nombre();
        this.correoElectronico = datosRegistroUsuario.correoElectronico();
        this.contrasena = passwordEncoder.encode(datosRegistroUsuario.contrasena());
        this.perfiles.add(perfil);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfiles.stream()
                .map(perfil -> (GrantedAuthority) () -> perfil.getNombre().name())
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
