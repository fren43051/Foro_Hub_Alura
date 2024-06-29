package com.aluracursos.foro_hub.repository;


import com.aluracursos.foro_hub.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad Topico.
 */
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByAutorId(Long autorId);
}
