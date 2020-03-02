package fr.commerce.ecommerce.rest;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * A simple example of how to setup a JAX-WS Web Service. It can say hello to everyone or to someone in particular.
 *
 * @author lnewson@redhat.com
 * @author Yoshimasa Tanabe
 *
 */
@WebService(targetNamespace = "http://ecommerce.io/TransactionUserService")
public interface TransactionUserService {

    /**
     * Say hello to someone precisely
     *
     * @param user to refund
     * @return true if ok.
     */
    @WebMethod
    public boolean refundCustomer(User user, double amount);

    /**
     * Say hello to someone precisely
     *
     * @param user to debit
     * @return true if ok.
     */
    @WebMethod
    public boolean debitCustomer(User user, double amount);


}
