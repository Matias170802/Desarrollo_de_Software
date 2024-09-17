package org.example;

import org.example.Entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("matibd");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("ESTAMOS LABURANDO");
        try {
            entityManager.getTransaction().begin()

            Factura factura1 = Factura.builder()
                    .fecha("12/04/2024")
                    .numero(12)
                    .build();

            Cliente cliente = Cliente.builder()
                    .nombre("Matias")
                    .apellido("Anselmi")
                    .dni(44252665)
                    .build();

            Domicilio domicilio = Domicilio.builder()
                    .nombreCalle("Echeverria")
                    .numero(2000)
                    .build();


            cliente.setDomicilio(domicilio);

            domicilio.setCliente(cliente);

            factura1.setCliente(cliente);

            Categoria perecederos = Categoria.builder()
                    .denominacion("Perecederos")
                    .build();

            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();

            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            Articulo art1 = Articulo.builder()
                    .denominacion("Yogurt")
                    .cantidad(200)
                    .precio(40)
                    .build();

            Articulo art2 = Articulo.builder()
                    .denominacion("Detergente")
                    .cantidad(300)
                    .precio(20)
                    .build();

            Articulo art3 = Articulo.builder()
                    .denominacion("Fideos")
                    .cantidad(300)
                    .precio(15)
                    .build();

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            perecederos.getArticulos().add(art1);
            lacteos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            art3.getCategorias().add(perecederos);
            perecederos.getArticulos().add(art3);

            DetalleFactura det1 = DetalleFactura.builder()
                    .build();

            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(80);

            art1.getDetalleFacturas().add(det1);

            entityManager.persist(cliente);// Guarda el objeto `cliente` en el contexto de persistencia (prepara para insertarlo en la base de datos).

            entityManager.flush();// Envía los cambios pendientes en el contexto de persistencia a la base de datos (sin hacer commit).

            entityManager.getTransaction().commit();// Confirma la transacción, haciendo permanentes los cambios en la base de datos.

        }catch (Exception e){
            entityManager.getTransaction().rollback();
            System.out.println("No se pudo Persistir");
        }
        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
