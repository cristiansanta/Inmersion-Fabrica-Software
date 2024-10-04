package com.sena.gestionProyectos.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String username;
    private String password;
}
