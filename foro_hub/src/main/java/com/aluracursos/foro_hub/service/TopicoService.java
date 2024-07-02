package com.aluracursos.foro_hub.service;

import com.aluracursos.foro_hub.config.DuplicateTopicException;
import com.aluracursos.foro_hub.dto.TopicoRecord;
import com.aluracursos.foro_hub.dto.TopicoResponse;
import com.aluracursos.foro_hub.enums.StatusTopico;
import com.aluracursos.foro_hub.models.Curso;
import com.aluracursos.foro_hub.models.Topico;
import com.aluracursos.foro_hub.models.Usuario;
import com.aluracursos.foro_hub.repository.CursoRepository;
import com.aluracursos.foro_hub.repository.TopicoRepository;
import com.aluracursos.foro_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoResponse registrarTopico(TopicoRecord topicoRecord) {
        Usuario autor = usuarioRepository.findById(topicoRecord.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findByNombre(topicoRecord.nombreCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Verificar si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.findByTituloAndMensaje(topicoRecord.titulo(), topicoRecord.mensaje()).isPresent()) {
            throw new DuplicateTopicException("Este tópico ya está registrado en su base de datos.");
        }


        Topico topico = new Topico();
        topico.setTitulo(topicoRecord.titulo());
        topico.setMensaje(topicoRecord.mensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(StatusTopico.ABIERTO); // Valor predeterminado para status
        topico.setAutor(autor);
        topico.setCurso(curso);

        topico = topicoRepository.save(topico);

        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                autor.getId(),
                autor.getNombre(),
                curso.getId(),
                curso.getNombre()
        );
    }

    public Page<TopicoResponse> listarTopicos(int page, int size, String sort, String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        return topicoRepository.findAllByStatus(StatusTopico.ABIERTO, pageable)
                .map(this::convertirTopicoAResponse);
    }

    public Page<TopicoResponse> listarTopicosCerrados(int page, int size, String sort, String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        Page<TopicoResponse> topicosCerrados = topicoRepository.findAllByStatus(StatusTopico.CERRADO, pageable)
                .map(this::convertirTopicoAResponse);

        if (topicosCerrados.isEmpty()) {
            throw new RuntimeException("No hay tópicos cerrados."); // O una excepción más específica
        }

        return topicosCerrados;
    }

    private TopicoResponse convertirTopicoAResponse(Topico topico) {
        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getAutor().getNombre(),
                topico.getCurso().getId(),
                topico.getCurso().getNombre()
        );
    }

    // En TopicoService.java
    public TopicoResponse actualizarTopico(Long id, TopicoRecord topicoRecord) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        Usuario autor = usuarioRepository.findById(topicoRecord.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findByNombre(topicoRecord.nombreCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        topico.setTitulo(topicoRecord.titulo());
        topico.setMensaje(topicoRecord.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        // Si el tópico está CERRADO, cámbialo a ABIERTO
        if (topico.getStatus() == StatusTopico.CERRADO) {
            topico.setStatus(StatusTopico.ABIERTO);
        }

        // Guarda el tópico actualizado
        topico = topicoRepository.save(topico);

        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                autor.getId(),
                autor.getNombre(),
                curso.getId(),
                curso.getNombre()
        );
    }

    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        topico.setStatus(StatusTopico.CERRADO); // Cambiar el estado
        topicoRepository.save(topico); // Guardar el cambio
    }
}
