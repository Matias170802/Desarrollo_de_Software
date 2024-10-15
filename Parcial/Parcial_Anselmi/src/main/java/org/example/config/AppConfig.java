package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example") // Cambia esto al paquete correcto
public class AppConfig {
    // Configuraciones adicionales si es necesario
}
