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
@WebService(serviceName = "api/UserTransactionService", portName = "api/UserTransaction", name = "api/UserTransaction", endpointInterface = "fr.commerce.ecommerce.rest.TransactionUserService",
        targetNamespace = "http://localhost:8080/api/TransactionUserService")
public class TransactionUserServiceImpl implements TransactionUserService {

    @PersistenceContext(unitName = "MyPU")
    EntityManager em;

    @Override
    public boolean refundCustomer(UserInputSoap user) {
        User userDb = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", user.getId())
                .getSingleResult();

        // TODO CALL BANK WEB SERVICE;
        return true;
    }

    @Override
    public boolean debitCustomer(UserInputSoap user) {
        User userDb = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", user.getId())
                .getSingleResult();

        // TODO CALL BANK WEB SERVICE;
        return true;
    }
}
