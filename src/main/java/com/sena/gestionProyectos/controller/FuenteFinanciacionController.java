package com.sena.gestionProyectos.controller;

import com.sena.gestionProyectos.dto.FuenteFinanciacionDTO;
import com.sena.gestionProyectos.service.FuenteFinanciacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuentes-financiacion")
public class FuenteFinanciacionController {

    @Autowired
    private FuenteFinanciacionService fuenteFinanciacionService;

    @GetMapping
    public List<FuenteFinanciacionDTO> getAllFuentesFinanciacion() {
        return fuenteFinanciacionService.getAllFuentesFinanciacion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuenteFinanciacionDTO> getFuenteFinanciacionById(@PathVariable Long id) {
        FuenteFinanciacionDTO fuenteFinanciacionDTO = fuenteFinanciacionService.getFuenteFinanciacionById(id);
        return ResponseEntity.ok(fuenteFinanciacionDTO);
    }

    @PostMapping
    public ResponseEntity<FuenteFinanciacionDTO> createFuenteFinanciacion(@RequestBody FuenteFinanciacionDTO fuenteFinanciacionDTO) {
        FuenteFinanciacionDTO createdFuenteFinanciacion = fuenteFinanciacionService.createFuenteFinanciacion(fuenteFinanciacionDTO);
        return ResponseEntity.ok(createdFuenteFinanciacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuenteFinanciacionDTO> updateFuenteFinanciacion(@PathVariable Long id, @RequestBody FuenteFinanciacionDTO fuenteFinanciacionDTO) {
        FuenteFinanciacionDTO updatedFuenteFinanciacion = fuenteFinanciacionService.updateFuenteFinanciacion(id, fuenteFinanciacionDTO);
        return ResponseEntity.ok(updatedFuenteFinanciacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuenteFinanciacion(@PathVariable Long id) {
        fuenteFinanciacionService.deleteFuenteFinanciacion(id);
        return ResponseEntity.noContent().build();
    }
}