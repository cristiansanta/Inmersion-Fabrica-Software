package com.sena.gestionProyectos.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiraTecnicaDTO {
    private Long id;
    private Long regionalId;
    private Long centroFormacionId;
    private Long fuenteFinanciacionId;
    private String objetivoGeneral;
    private String resultadoEsperado;
    private BigDecimal valorTotal;
    private String observaciones;
}
