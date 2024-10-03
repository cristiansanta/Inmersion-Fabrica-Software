package com.sena.gestionProyectos.controller;

import com.sena.gestionProyectos.dto.RegionalDTO;
import com.sena.gestionProyectos.model.Regional;
import com.sena.gestionProyectos.service.RegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regionales")
public class RegionalController {

    @Autowired
    private RegionalService regionalService;

    @GetMapping
    public List<RegionalDTO> getAllRegionales() {
        return regionalService.getAllRegionales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionalDTO> getRegionalById(@PathVariable Long id) {
        RegionalDTO regionalDTO = regionalService.getRegionalById(id);
        return ResponseEntity.ok(regionalDTO);
    }

    @PostMapping
    public ResponseEntity<RegionalDTO> createRegional(@RequestBody RegionalDTO regionalDTO) {
        RegionalDTO createdRegional = regionalService.createRegional(regionalDTO);
        return ResponseEntity.ok(createdRegional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionalDTO> updateRegional(@PathVariable Long id, @RequestBody RegionalDTO regionalDTO) {
        RegionalDTO updatedRegional = regionalService.updateRegional(id, regionalDTO);
        return ResponseEntity.ok(updatedRegional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegional(@PathVariable Long id) {
        regionalService.deleteRegional(id);
        return ResponseEntity.noContent().build();
    }
}