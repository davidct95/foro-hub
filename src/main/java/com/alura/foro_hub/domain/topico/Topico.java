package com.alura.foro_hub.domain.topico;

import com.alura.foro_hub.domain.curso.Curso;
import com.alura.foro_hub.domain.respuesta.Respuesta;
import com.alura.foro_hub.domain.topico.dto.DatosActualizarTopico;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String titulo;
    @Column(nullable = false, unique = true)
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;
    private boolean eliminado;
    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Topico(DatosRegistroTopico datosRegistroTopico, Curso curso, Usuario usuario) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = datosRegistroTopico.estado();
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico, Curso curso){
        this.titulo = datosActualizarTopico.titulo();
        this.mensaje = datosActualizarTopico.mensaje();
        this.estado = datosActualizarTopico.estado();
        this.curso = curso;
    }

    public void eliminarRegistroLogicamente() {
        this.eliminado = true;
    }

}
