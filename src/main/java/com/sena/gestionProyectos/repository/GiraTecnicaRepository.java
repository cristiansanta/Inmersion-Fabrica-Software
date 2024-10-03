package com.sena.gestionProyectos.repository;

import com.sena.gestionProyectos.model.GiraTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiraTecnicaRepository extends JpaRepository<GiraTecnica, Long> {
}