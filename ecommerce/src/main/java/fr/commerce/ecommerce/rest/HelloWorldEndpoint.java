package fr.commerce.ecommerce.rest;


import org.infinispan.factories.annotations.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
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

@Path("/hello")
public class HelloWorldEndpoint {

    @GET
    @Path("db")
    @Produces("application/json")
    public Response doGetDb() throws NamingException, SQLException {
        EntityManagerFactory emf =
                Persistence
                        .createEntityManagerFactory("MyPU");
        EntityManager em = emf.createEntityManager();
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        return Response.ok(products).build();
    }

    @GET
    @Produces("text/plain")
    public Response doGet() throws NamingException, SQLException {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jboss/datasources/EcommerceDS");
        Connection conn = ds.getConnection();
        try {

        } finally {
            conn.close();
        }
        return Response.ok("Hello from Thorntail!").build();
    }

}
