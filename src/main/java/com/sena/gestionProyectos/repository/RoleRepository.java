package com.sena.gestionProyectos.repository;

import com.sena.gestionProyectos.model.ERole;
import com.sena.gestionProyectos.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}