package com.sena.gestionProyectos.service;

import com.sena.gestionProyectos.dto.GiraTecnicaDTO;
import com.sena.gestionProyectos.exceptions.ResourceNotFoundException;
import com.sena.gestionProyectos.model.CentroFormacion;
import com.sena.gestionProyectos.model.FuenteFinanciacion;
import com.sena.gestionProyectos.model.GiraTecnica;
import com.sena.gestionProyectos.model.Regional;
import com.sena.gestionProyectos.repository.GiraTecnicaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiraTecnicaService {

    @Autowired
    private GiraTecnicaRepository giraTecnicaRepository;

    @Autowired
    private RegionalService regionalService;

    @Autowired
    private CentroFormacionService centroFormacionService;

    @Autowired
    private FuenteFinanciacionService fuenteFinanciacionService;

    public List<GiraTecnicaDTO> getAllGirasTecnicas() {
        return giraTecnicaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GiraTecnicaDTO getGiraTecnicaById(Long id) {
        GiraTecnica giraTecnica = getGiraTecnicaEntityById(id);
        return convertToDTO(giraTecnica);
    }

    public GiraTecnica getGiraTecnicaEntityById(Long id) {
        return giraTecnicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gira Técnica no encontrada con id: " + id));
    }

    public GiraTecnicaDTO createGiraTecnica(GiraTecnicaDTO giraTecnicaDTO) {
        GiraTecnica giraTecnica = new GiraTecnica();
        updateGiraTecnicaFromDTO(giraTecnica, giraTecnicaDTO);
        GiraTecnica savedGiraTecnica = giraTecnicaRepository.save(giraTecnica);
        return convertToDTO(savedGiraTecnica);
    }

    public GiraTecnicaDTO updateGiraTecnica(Long id, GiraTecnicaDTO giraTecnicaDTO) {
        GiraTecnica giraTecnica = getGiraTecnicaEntityById(id);
        updateGiraTecnicaFromDTO(giraTecnica, giraTecnicaDTO);
        GiraTecnica updatedGiraTecnica = giraTecnicaRepository.save(giraTecnica);
        return convertToDTO(updatedGiraTecnica);
    }

    public void deleteGiraTecnica(Long id) {
        if (!giraTecnicaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Gira Técnica no encontrada con id: " + id);
        }
        giraTecnicaRepository.deleteById(id);
    }

    private void updateGiraTecnicaFromDTO(GiraTecnica giraTecnica, GiraTecnicaDTO giraTecnicaDTO) {
        Regional regional = regionalService.getRegionalEntityById(giraTecnicaDTO.getRegionalId());
        CentroFormacion centroFormacion = centroFormacionService.getCentroFormacionEntityById(giraTecnicaDTO.getCentroFormacionId());
        FuenteFinanciacion fuenteFinanciacion = fuenteFinanciacionService.getFuenteFinanciacionEntityById(giraTecnicaDTO.getFuenteFinanciacionId());

        giraTecnica.setRegional(regional);
        giraTecnica.setCentroFormacion(centroFormacion);
        giraTecnica.setFuenteFinanciacion(fuenteFinanciacion);
        giraTecnica.setObjetivoGeneral(giraTecnicaDTO.getObjetivoGeneral());
        giraTecnica.setResultadoEsperado(giraTecnicaDTO.getResultadoEsperado());
        giraTecnica.setValorTotal(giraTecnicaDTO.getValorTotal());
        giraTecnica.setObservaciones(giraTecnicaDTO.getObservaciones());
    }

    private GiraTecnicaDTO convertToDTO(GiraTecnica giraTecnica) {
        return new GiraTecnicaDTO(
                giraTecnica.getId(),
                giraTecnica.getRegional().getId(),
                giraTecnica.getCentroFormacion().getId(),
                giraTecnica.getFuenteFinanciacion().getId(),
                giraTecnica.getObjetivoGeneral(),
                giraTecnica.getResultadoEsperado(),
                giraTecnica.getValorTotal(),
                giraTecnica.getObservaciones()
        );
    }
}