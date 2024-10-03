package com.sena.gestionProyectos.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CentroFormacionDTO {
    private Long id;
    private String nombre;
    private Long regionalId;
}