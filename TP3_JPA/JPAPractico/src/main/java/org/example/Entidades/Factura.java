package org.example.Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private int numero;
    private int total;

    //RELACIONES
    //Cliente
    @ManyToOne(cascade = CascadeType.PERSIST)//Si tocamos factura no vamos a cambiar a cliente
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    // Unidireccional
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)// es ALL y el orphanRemoval = true para cuando yo elimine factura se van a eliminar sus detalles, (Composicion)
    private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();

}
