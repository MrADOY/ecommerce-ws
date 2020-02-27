package fr.commerce.ecommerce.rest;


import org.infinispan.factories.annotations.Inject;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/products")
@ApplicationScoped
public class ProductsController {

    @PersistenceContext(unitName = "MyPU")
    EntityManager em;

    @GET
    @Produces("application/json")
    public Response getAllProducts() throws NamingException, SQLException {
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        return Response.ok(products).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") int id) {
        Product product = em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        return Response.ok(product).build();
    }

}
