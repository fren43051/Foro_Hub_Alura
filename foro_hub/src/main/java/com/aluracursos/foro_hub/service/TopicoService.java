package com.aluracursos.foro_hub.service;


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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TopicoResponse> listarTopicos() {
        return topicoRepository.findAll().stream()
                .map(topico -> new TopicoResponse(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor().getId(),
                        topico.getAutor().getNombre(),
                        topico.getCurso().getId(),
                        topico.getCurso().getNombre()
                ))
                .collect(Collectors.toList());
    }

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
        topicoRepository.delete(topico);
    }
}


