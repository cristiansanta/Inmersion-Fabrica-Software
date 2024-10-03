package com.sena.gestionProyectos.service;

import com.sena.gestionProyectos.dto.CentroFormacionDTO;
import com.sena.gestionProyectos.exceptions.ResourceNotFoundException;
import com.sena.gestionProyectos.model.CentroFormacion;
import com.sena.gestionProyectos.model.Regional;
import com.sena.gestionProyectos.repository.CentroFormacionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentroFormacionService {

    @Autowired
    private CentroFormacionRepository centroFormacionRepository;

    @Autowired
    private RegionalService regionalService;

    public List<CentroFormacionDTO> getAllCentrosFormacion() {
        return centroFormacionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CentroFormacionDTO getCentroFormacionById(Long id) {
        CentroFormacion centroFormacion = getCentroFormacionEntityById(id);
        return convertToDTO(centroFormacion);
    }
    public CentroFormacion getCentroFormacionEntityById(Long id) {
        return centroFormacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centro de Formación no encontrado con id: " + id));
    }

    public CentroFormacionDTO createCentroFormacion(CentroFormacionDTO centroFormacionDTO) {
        Regional regional = regionalService.getRegionalEntityById(centroFormacionDTO.getRegionalId());
        CentroFormacion centroFormacion = new CentroFormacion();
        centroFormacion.setNombre(centroFormacionDTO.getNombre());
        centroFormacion.setRegional(regional);
        CentroFormacion savedCentroFormacion = centroFormacionRepository.save(centroFormacion);
        return convertToDTO(savedCentroFormacion);
    }

    public CentroFormacionDTO updateCentroFormacion(Long id, CentroFormacionDTO centroFormacionDTO) {
        CentroFormacion centroFormacion = centroFormacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centro de Formación no encontrado con id: " + id));
        Regional regional = regionalService.getRegionalEntityById(centroFormacionDTO.getRegionalId());
        centroFormacion.setNombre(centroFormacionDTO.getNombre());
        centroFormacion.setRegional(regional);
        CentroFormacion updatedCentroFormacion = centroFormacionRepository.save(centroFormacion);
        return convertToDTO(updatedCentroFormacion);
    }

    public void deleteCentroFormacion(Long id) {
        if (!centroFormacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Centro de Formación no encontrado con id: " + id);
        }
        centroFormacionRepository.deleteById(id);
    }

    private CentroFormacionDTO convertToDTO(CentroFormacion centroFormacion) {
        return new CentroFormacionDTO(
                centroFormacion.getId(),
                centroFormacion.getNombre(),
                centroFormacion.getRegional().getId()
        );
    }
}