package com.aluracursos.foro_hub.repository;


import com.aluracursos.foro_hub.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

