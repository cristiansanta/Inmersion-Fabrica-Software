package com.sena.gestionProyectos.repository;

import com.sena.gestionProyectos.model.CentroFormacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroFormacionRepository extends JpaRepository<CentroFormacion, Long> {
}
