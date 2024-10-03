package com.example.inicial1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "autor")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Audited
public class Autor extends Base {

    private String nombre;
    private String apellido;

    @Column(name = "biografia",length = 1500)
    private String biografia;
}
