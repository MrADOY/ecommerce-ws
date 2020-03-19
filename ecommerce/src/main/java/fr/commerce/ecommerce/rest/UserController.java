package fr.commerce.ecommerce.rest;

import fr.commerce.ecommerce.rest.model.ConnexionUserOdt;
import fr.commerce.ecommerce.rest.model.ConnexionUserResponseOdt;
import fr.commerce.ecommerce.rest.model.RegisterUserOdt;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import javax.ws.rs.*;
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
    public Response authentificate(ConnexionUserOdt user) {
        User userDb = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", user.getEmail())
                .getSingleResult();
        if (userDb != null) {
            if (userDb.getPassword().equals(user.getPassword())) {
                ConnexionUserResponseOdt toReturn = ConnexionUserResponseOdt.builder()
                        .id(userDb.getId())
                        .email(userDb.getEmail())
                        .name(userDb.getName())
                        .firstname(userDb.getFirstname())
                        .build();
                return Response.ok(toReturn).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("register")
    @Produces("application/json")
    public Response inscription(RegisterUserOdt user) throws Exception {
        this.transaction.begin();
        User u = User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .bankCardNumber(user.getBankCardNumber())
                .firstname(user.getFirstname())
                .name(user.getName())
                .build();
        this.em.persist(u);
        this.transaction.commit();

        ConnexionUserResponseOdt toReturn = ConnexionUserResponseOdt.builder()
                .id(u.getId())
                .email(u.getEmail())
                .firstname(u.getFirstname())
                .name(u.getName())
                .build();

        return Response.ok(toReturn).build();
    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") int id) {
        User user = getUserWithId(id);
        return Response.ok(user).build();
    }

    private User getUserWithId(int id) {
        return em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
