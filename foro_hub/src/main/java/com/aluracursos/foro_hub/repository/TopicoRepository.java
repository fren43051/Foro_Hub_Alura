package com.aluracursos.foro_hub.repository;


import com.aluracursos.foro_hub.enums.StatusTopico;
import com.aluracursos.foro_hub.models.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repositorio para la entidad Topico.
 */
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findAllByStatus(StatusTopico status, Pageable pageable);
}

