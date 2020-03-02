package fr.commerce.ecommerce.rest;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The implementation of the HelloWorld JAX-WS Web Service.
 *
 * @author lnewson@redhat.com
 * @author Yoshimasa Tanabe
 */
@WebService(serviceName = "UserTransactionService", portName = "UserTransaction", name = "UserTransaction", endpointInterface = "fr.commerce.ecommerce.rest.TransactionUserService",
        targetNamespace = "http://ecommerce.io/TransactionUserService")
public class TransactionUserServiceImpl implements TransactionUserService {

    @PersistenceContext(unitName = "MyPU")
    EntityManager em;

    @Override
    public boolean refundCustomer(User user, double amount) {
        User userDb = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", user.getId())
                .getSingleResult();

        // TODO CALL BANK WEB SERVICE;
        return true;
    }

    @Override
    public boolean debitCustomer(User user, double amount) {
        User userDb = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", user.getId())
                .getSingleResult();

        // TODO CALL BANK WEB SERVICE;
        return true;
    }
}
