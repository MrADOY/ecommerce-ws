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
@WebService(targetNamespace = "http://localhost:8080/api/TransactionUserService")
public interface TransactionUserService {

    /**
     * Say hello to someone precisely
     *
     * @param user to refund
     * @return true if ok.
     */
    @WebMethod
    public boolean refundCustomer(UserInputSoap user);

    /**
     * Say hello to someone precisely
     *
     * @param user to debit
     * @return true if ok.
     */
    @WebMethod
    public boolean debitCustomer(UserInputSoap user);


}
