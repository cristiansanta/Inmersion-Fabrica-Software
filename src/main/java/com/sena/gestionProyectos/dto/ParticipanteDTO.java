package com.sena.gestionProyectos.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDTO {
    private Long id;
    private Long giraTecnicaId;
    private String nombre;
    private String rol;
}
