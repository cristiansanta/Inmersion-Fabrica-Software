package com.sena.gestionProyectos.controller;

import com.sena.gestionProyectos.dto.JwtResponse;
import com.sena.gestionProyectos.dto.LoginRequest;
import com.sena.gestionProyectos.dto.MessageResponse;
import com.sena.gestionProyectos.dto.SignupRequest;
import com.sena.gestionProyectos.model.Usuario;
import com.sena.gestionProyectos.repository.UsuarioRepository;
import com.sena.gestionProyectos.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication.getName());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (usuarioRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        Usuario usuario = new Usuario(
                signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}