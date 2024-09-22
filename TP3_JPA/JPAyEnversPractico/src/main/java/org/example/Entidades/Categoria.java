package org.example.Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;

    //Relaciones
    //Categoria
    @ManyToMany(mappedBy = "categorias")
    @Builder.Default //@Builder.Default le indica a Lombok que debe respetar el valor por defecto que hayas asignado al campo, incluso cuando uses el Builder, a menos que se sobrescriba explícitamente.
    private List<Articulo> articulos = new ArrayList<Articulo>();
}
