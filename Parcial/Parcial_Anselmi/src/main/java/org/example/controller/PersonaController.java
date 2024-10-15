package org.example.controller;

import org.example.entities.Persona;
import org.example.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/personas")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<String> crearPersona(@RequestBody Persona persona) {
        try {
            boolean esMutante = personaService.verificarMutante(persona);
            persona.setEsMutante(esMutante);
            return ResponseEntity.ok("Persona procesada. Mutante: " + esMutante);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error en la verificación: " + e.getMessage());
        }
    }
}
