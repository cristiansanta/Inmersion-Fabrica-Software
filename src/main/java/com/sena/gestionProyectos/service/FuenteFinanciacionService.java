package com.sena.gestionProyectos.service;

import com.sena.gestionProyectos.dto.FuenteFinanciacionDTO;
import com.sena.gestionProyectos.exceptions.ResourceNotFoundException;
import com.sena.gestionProyectos.model.FuenteFinanciacion;
import com.sena.gestionProyectos.repository.FuenteFinanciacionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuenteFinanciacionService {

    @Autowired
    private FuenteFinanciacionRepository fuenteFinanciacionRepository;

    public List<FuenteFinanciacionDTO> getAllFuentesFinanciacion() {
        return fuenteFinanciacionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FuenteFinanciacionDTO getFuenteFinanciacionById(Long id) {
        FuenteFinanciacion fuenteFinanciacion = getFuenteFinanciacionEntityById(id);
        return convertToDTO(fuenteFinanciacion);
    }

    public FuenteFinanciacion getFuenteFinanciacionEntityById(Long id) {
        return fuenteFinanciacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fuente de Financiación no encontrada con id: " + id));
    }

    public FuenteFinanciacionDTO createFuenteFinanciacion(FuenteFinanciacionDTO fuenteFinanciacionDTO) {
        FuenteFinanciacion fuenteFinanciacion = new FuenteFinanciacion();
        fuenteFinanciacion.setNombre(fuenteFinanciacionDTO.getNombre());
        FuenteFinanciacion savedFuenteFinanciacion = fuenteFinanciacionRepository.save(fuenteFinanciacion);
        return convertToDTO(savedFuenteFinanciacion);
    }

    public FuenteFinanciacionDTO updateFuenteFinanciacion(Long id, FuenteFinanciacionDTO fuenteFinanciacionDTO) {
        FuenteFinanciacion fuenteFinanciacion = getFuenteFinanciacionEntityById(id);
        fuenteFinanciacion.setNombre(fuenteFinanciacionDTO.getNombre());
        FuenteFinanciacion updatedFuenteFinanciacion = fuenteFinanciacionRepository.save(fuenteFinanciacion);
        return convertToDTO(updatedFuenteFinanciacion);
    }

    public void deleteFuenteFinanciacion(Long id) {
        if (!fuenteFinanciacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fuente de Financiación no encontrada con id: " + id);
        }
        fuenteFinanciacionRepository.deleteById(id);
    }

    private FuenteFinanciacionDTO convertToDTO(FuenteFinanciacion fuenteFinanciacion) {
        return new FuenteFinanciacionDTO(fuenteFinanciacion.getId(), fuenteFinanciacion.getNombre());
    }
}