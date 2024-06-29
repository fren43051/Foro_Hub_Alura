package com.aluracursos.foro_hub.dto;



import com.aluracursos.foro_hub.enums.StatusTopico;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        Long idAutor,
        String nombreAutor,
        Long idCurso,
        String nombreCurso
) {}

