package org.example.Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apellido;
    private String nombre;
    private int dni;

    //RELACIONES
    //Domicilio
    @OneToOne(cascade = CascadeType.ALL) //Cualquier cambio que yo realize en cliente se va a ver reflejado en el domicilio del mismo
    @JoinColumn(name = "fk_domicilio") // Creamos la clave foranea de domicilio
    private Domicilio domicilio;

    //Factura
    @OneToMany(mappedBy = "cliente")//Bidirecional
    private List<Factura> facturas = new ArrayList<Factura>();


}
