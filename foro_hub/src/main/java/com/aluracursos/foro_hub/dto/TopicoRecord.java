package com.aluracursos.foro_hub.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TopicoRecord(
        @NotNull Long idUsuario,
        @NotEmpty String mensaje,
        @NotEmpty String titulo,
        @NotEmpty String nombreCurso // nombre del curso en lugar de id
) {}




