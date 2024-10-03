package com.sena.gestionProyectos.repository;

import com.sena.gestionProyectos.model.FuenteFinanciacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuenteFinanciacionRepository extends JpaRepository<FuenteFinanciacion, Long> {
}