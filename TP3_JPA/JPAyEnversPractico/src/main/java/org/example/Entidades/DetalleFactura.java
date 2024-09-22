package org.example.Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DetalleFactura")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class DetalleFactura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int cantidad;
    private int subtotal;

    //RELACIONES
    //Articulo
    @ManyToOne(cascade = CascadeType.PERSIST)//Si tocamos detalle factura no vamos a cambiar a articulo
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "detalle_factura_fk")
    private Factura factura;

}
