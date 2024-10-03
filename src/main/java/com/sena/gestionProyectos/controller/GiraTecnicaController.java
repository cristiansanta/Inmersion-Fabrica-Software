package com.sena.gestionProyectos.controller;

import com.sena.gestionProyectos.dto.GiraTecnicaDTO;
import com.sena.gestionProyectos.service.GiraTecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/giras-tecnicas")
public class GiraTecnicaController {

    @Autowired
    private GiraTecnicaService giraTecnicaService;

    @GetMapping
    public List<GiraTecnicaDTO> getAllGirasTecnicas() {
        return giraTecnicaService.getAllGirasTecnicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiraTecnicaDTO> getGiraTecnicaById(@PathVariable Long id) {
        GiraTecnicaDTO giraTecnicaDTO = giraTecnicaService.getGiraTecnicaById(id);
        return ResponseEntity.ok(giraTecnicaDTO);
    }

    @PostMapping
    public ResponseEntity<GiraTecnicaDTO> createGiraTecnica(@RequestBody GiraTecnicaDTO giraTecnicaDTO) {
        GiraTecnicaDTO createdGiraTecnica = giraTecnicaService.createGiraTecnica(giraTecnicaDTO);
        return ResponseEntity.ok(createdGiraTecnica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiraTecnicaDTO> updateGiraTecnica(@PathVariable Long id, @RequestBody GiraTecnicaDTO giraTecnicaDTO) {
        GiraTecnicaDTO updatedGiraTecnica = giraTecnicaService.updateGiraTecnica(id, giraTecnicaDTO);
        return ResponseEntity.ok(updatedGiraTecnica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiraTecnica(@PathVariable Long id) {
        giraTecnicaService.deleteGiraTecnica(id);
        return ResponseEntity.noContent().build();
    }
}