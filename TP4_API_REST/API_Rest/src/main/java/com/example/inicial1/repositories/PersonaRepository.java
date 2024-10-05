package com.example.inicial1.repositories;

import com.example.inicial1.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

    List<Persona> findByNombreContainingApellidoContaining(String nombre,String apellido);// Metodos de Querry hay miles no se usan mucho por su extenso nombre

    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE %?:filtro% OR p.apellido LIKE %:filtro%")//JPQL hacemos las consultas como en SQL
    List<Persona> search(@Param("filtro") String filtro);


    @Query(
            value ="SELECT * FROM persona WHERE persona.nombre LIKE %?:filtro% OR persona.apellido LIKE %:filtro%",
            nativeQuery = true
    )
    List<Persona> searchNativo(@Param("filtro") String filtro);
}