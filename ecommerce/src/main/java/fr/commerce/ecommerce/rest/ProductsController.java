package fr.commerce.ecommerce.rest;


import fr.commerce.ecommerce.rest.model.CreateProductOdt;
import fr.commerce.ecommerce.rest.model.Product;
import fr.commerce.ecommerce.rest.model.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/products")
public class ProductsController {

    @PersistenceContext(unitName = "MyPU")
    EntityManager em;

    @Resource
    private UserTransaction transaction;

    @GET
    @Produces("application/json")
    public Response getAllProducts() throws NamingException, SQLException {
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        return Response.ok(products).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getProductsById(@PathParam("id") int id) {
        Product product = getProductWithId(id);
        return Response.ok(product).build();
    }

    @GET
    @Path("{id}/buy")
    @Produces("application/json")
    public Response productNotAvailable(@PathParam("id") int id) throws Exception{
        this.transaction.begin();
        Product product = getProductWithId(id);
        product.setAvailable(false);
        this.em.persist(product);
        this.transaction.commit();
        return Response.ok(product).build();
    }

    private Product getProductWithId(int id) {
        return em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    private User getUserWithId(int id) {
        return em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    @POST
    @Path("create")
    @Produces("application/json")
    public Response createProduct(CreateProductOdt productToCreate) throws Exception {
        this.transaction.begin();
        Product p = Product.builder()
                .name(productToCreate.getName())
                .description(productToCreate.getDescription())
                .ownerId(getUserWithId(productToCreate.getOwnerId()).getId())
                .price(productToCreate.getPrice())
                .build();
        this.em.persist(p);
        this.transaction.commit();
        return Response.ok().build();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public UserTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(UserTransaction transaction) {
        this.transaction = transaction;
    }
}
