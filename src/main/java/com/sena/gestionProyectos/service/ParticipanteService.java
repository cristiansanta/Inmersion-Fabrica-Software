package com.sena.gestionProyectos.service;

import com.sena.gestionProyectos.dto.ParticipanteDTO;
import com.sena.gestionProyectos.exceptions.ResourceNotFoundException;
import com.sena.gestionProyectos.model.GiraTecnica;
import com.sena.gestionProyectos.model.Participante;
import com.sena.gestionProyectos.repository.ParticipanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private GiraTecnicaService giraTecnicaService;

    public List<ParticipanteDTO> getAllParticipantes() {
        return participanteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ParticipanteDTO getParticipanteById(Long id) {
        Participante participante = getParticipanteEntityById(id);
        return convertToDTO(participante);
    }

    public Participante getParticipanteEntityById(Long id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participante no encontrado con id: " + id));
    }

    public ParticipanteDTO createParticipante(ParticipanteDTO participanteDTO) {
        Participante participante = new Participante();
        updateParticipanteFromDTO(participante, participanteDTO);
        Participante savedParticipante = participanteRepository.save(participante);
        return convertToDTO(savedParticipante);
    }

    public ParticipanteDTO updateParticipante(Long id, ParticipanteDTO participanteDTO) {
        Participante participante = getParticipanteEntityById(id);
        updateParticipanteFromDTO(participante, participanteDTO);
        Participante updatedParticipante = participanteRepository.save(participante);
        return convertToDTO(updatedParticipante);
    }

    public void deleteParticipante(Long id) {
        if (!participanteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Participante no encontrado con id: " + id);
        }
        participanteRepository.deleteById(id);
    }

    private void updateParticipanteFromDTO(Participante participante, ParticipanteDTO participanteDTO) {
        GiraTecnica giraTecnica = giraTecnicaService.getGiraTecnicaEntityById(participanteDTO.getGiraTecnicaId());
        participante.setGiraTecnica(giraTecnica);
        participante.setNombre(participanteDTO.getNombre());
        participante.setRol(participanteDTO.getRol());
    }

    private ParticipanteDTO convertToDTO(Participante participante) {
        return new ParticipanteDTO(
                participante.getId(),
                participante.getGiraTecnica().getId(),
                participante.getNombre(),
                participante.getRol()
        );
    }
}