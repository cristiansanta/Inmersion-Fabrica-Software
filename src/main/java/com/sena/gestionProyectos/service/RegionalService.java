package com.sena.gestionProyectos.service;

import com.sena.gestionProyectos.dto.RegionalDTO;
import com.sena.gestionProyectos.exceptions.ResourceNotFoundException;
import com.sena.gestionProyectos.model.Regional;
import com.sena.gestionProyectos.repository.RegionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionalService {

    @Autowired
    private RegionalRepository regionalRepository;

    public List<RegionalDTO> getAllRegionales() {
        return regionalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RegionalDTO getRegionalById(Long id) {
        Regional regional = getRegionalEntityById(id);
        return convertToDTO(regional);
    }

    public Regional getRegionalEntityById(Long id) {
        return regionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Regional no encontrada con id: " + id));
    }

    public RegionalDTO createRegional(RegionalDTO regionalDTO) {
        Regional regional = new Regional();
        regional.setNombre(regionalDTO.getNombre());
        Regional savedRegional = regionalRepository.save(regional);
        return convertToDTO(savedRegional);
    }

    public RegionalDTO updateRegional(Long id, RegionalDTO regionalDTO) {
        Regional regional = getRegionalEntityById(id);
        regional.setNombre(regionalDTO.getNombre());
        Regional updatedRegional = regionalRepository.save(regional);
        return convertToDTO(updatedRegional);
    }

    public void deleteRegional(Long id) {
        if (!regionalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Regional no encontrada con id: " + id);
        }
        regionalRepository.deleteById(id);
    }

    private RegionalDTO convertToDTO(Regional regional) {
        return new RegionalDTO(regional.getId(), regional.getNombre());
    }
}