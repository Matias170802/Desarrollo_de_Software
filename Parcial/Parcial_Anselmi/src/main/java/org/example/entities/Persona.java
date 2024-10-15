package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import java.util.List;

//@Entity
//@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Persona {
    private String[][] secuenciaADN;
    private boolean esMutante;       // Indica si la persona es mutante o no


}
