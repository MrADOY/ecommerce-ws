package fr.commerce.ecommerce.rest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("user")
public class UserController {

    @PersistenceContext(unitName = "MyPU")
    EntityManager em;

    @Resource
    private UserTransaction transaction ;

    @POST
    @Path("connexion")
    @Produces("application/json")
    public Response authentificate(User utilisateur) {
        User utilisateurDb = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", utilisateur.getEmail())
                .getSingleResult();
        if (utilisateurDb != null) {
            if (utilisateurDb.getPassword().equals(utilisateur.getPassword())) {
                return Response.ok(utilisateurDb).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("inscription")
    @Produces("application/json")
    public Response inscription(User utilisateur) throws Exception {
        this.transaction.begin();
        this.em.persist(utilisateur);
        this.transaction.commit();
        return Response.ok().build();
    }
}