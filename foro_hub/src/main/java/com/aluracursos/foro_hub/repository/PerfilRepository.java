package com.aluracursos.foro_hub.repository;


import com.aluracursos.foro_hub.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Perfil.
 */
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
