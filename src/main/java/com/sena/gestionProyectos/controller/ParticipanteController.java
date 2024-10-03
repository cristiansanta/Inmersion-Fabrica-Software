package com.sena.gestionProyectos.controller;

import com.sena.gestionProyectos.dto.ParticipanteDTO;
import com.sena.gestionProyectos.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @GetMapping
    public List<ParticipanteDTO> getAllParticipantes() {
        return participanteService.getAllParticipantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> getParticipanteById(@PathVariable Long id) {
        ParticipanteDTO participanteDTO = participanteService.getParticipanteById(id);
        return ResponseEntity.ok(participanteDTO);
    }

    @PostMapping
    public ResponseEntity<ParticipanteDTO> createParticipante(@RequestBody ParticipanteDTO participanteDTO) {
        ParticipanteDTO createdParticipante = participanteService.createParticipante(participanteDTO);
        return ResponseEntity.ok(createdParticipante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> updateParticipante(@PathVariable Long id, @RequestBody ParticipanteDTO participanteDTO) {
        ParticipanteDTO updatedParticipante = participanteService.updateParticipante(id, participanteDTO);
        return ResponseEntity.ok(updatedParticipante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Long id) {
        participanteService.deleteParticipante(id);
        return ResponseEntity.noContent().build();
    }
}