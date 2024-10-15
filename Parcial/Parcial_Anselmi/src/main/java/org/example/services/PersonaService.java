package org.example.services;

import org.example.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PersonaService {
    private final ApiClient apiClient;

    @Autowired
    public PersonaService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public boolean verificarMutante(Persona persona) throws IOException {
        return apiClient.isMutant(persona.getSecuenciaADN());
    }
}
