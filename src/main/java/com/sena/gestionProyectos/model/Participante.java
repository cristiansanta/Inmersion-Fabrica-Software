package com.sena.gestionProyectos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participantes")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gira_tecnica_id", nullable = false)
    private GiraTecnica giraTecnica;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String rol;
}