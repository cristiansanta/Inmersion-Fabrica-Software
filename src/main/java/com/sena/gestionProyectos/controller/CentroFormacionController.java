package com.sena.gestionProyectos.controller;

import com.sena.gestionProyectos.dto.CentroFormacionDTO;
import com.sena.gestionProyectos.service.CentroFormacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros-formacion")
public class CentroFormacionController {

    @Autowired
    private CentroFormacionService centroFormacionService;

    @GetMapping
    public List<CentroFormacionDTO> getAllCentrosFormacion() {
        return centroFormacionService.getAllCentrosFormacion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroFormacionDTO> getCentroFormacionById(@PathVariable Long id) {
        CentroFormacionDTO centroFormacionDTO = centroFormacionService.getCentroFormacionById(id);
        return ResponseEntity.ok(centroFormacionDTO);
    }

    @PostMapping
    public ResponseEntity<CentroFormacionDTO> createCentroFormacion(@RequestBody CentroFormacionDTO centroFormacionDTO) {
        CentroFormacionDTO createdCentroFormacion = centroFormacionService.createCentroFormacion(centroFormacionDTO);
        return ResponseEntity.ok(createdCentroFormacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroFormacionDTO> updateCentroFormacion(@PathVariable Long id, @RequestBody CentroFormacionDTO centroFormacionDTO) {
        CentroFormacionDTO updatedCentroFormacion = centroFormacionService.updateCentroFormacion(id, centroFormacionDTO);
        return ResponseEntity.ok(updatedCentroFormacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentroFormacion(@PathVariable Long id) {
        centroFormacionService.deleteCentroFormacion(id);
        return ResponseEntity.noContent().build();
    }
}