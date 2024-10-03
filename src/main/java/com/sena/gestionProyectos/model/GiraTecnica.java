package com.sena.gestionProyectos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "giras_tecnicas")
public class GiraTecnica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "regional_id", nullable = false)
    private Regional regional;

    @ManyToOne
    @JoinColumn(name = "centro_formacion_id", nullable = false)
    private CentroFormacion centroFormacion;

    @ManyToOne
    @JoinColumn(name = "fuente_financiacion_id", nullable = false)
    private FuenteFinanciacion fuenteFinanciacion;

    @Column(nullable = false)
    private String objetivoGeneral;

    @Column(nullable = false)
    private String resultadoEsperado;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    private String observaciones;
}