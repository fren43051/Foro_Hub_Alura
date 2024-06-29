package com.aluracursos.foro_hub.repository;


import com.aluracursos.foro_hub.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombre(String nombre);
}

