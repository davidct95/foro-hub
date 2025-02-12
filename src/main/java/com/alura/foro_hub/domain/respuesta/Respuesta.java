package com.alura.foro_hub.domain.respuesta;

import com.alura.foro_hub.domain.respuesta.dto.DatosActualizarRespuesta;
import com.alura.foro_hub.domain.respuesta.dto.DatosRegistroRespuesta;
import com.alura.foro_hub.domain.topico.Topico;
import com.alura.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topico")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    private boolean solucion;
    private boolean eliminado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Topico topico, Usuario usuario) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = datosRegistroRespuesta.solucion();
        this.usuario = usuario;
    }

    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta){
        this.mensaje = datosActualizarRespuesta.mensaje();
        this.solucion = datosActualizarRespuesta.solucion();
    }

    public void eliminarRegistroLogico() {
        this.eliminado = true;
    }
}
