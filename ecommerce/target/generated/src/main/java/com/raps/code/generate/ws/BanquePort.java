
package com.raps.code.generate.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "BanquePort", targetNamespace = "http://spring:8081/BanqueService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BanquePort {


    /**
     * 
     * @param refundCustomerRequest
     * @return
     *     returns com.raps.code.generate.ws.RefundCustomerResponse
     */
    @WebMethod
    @WebResult(name = "refundCustomerResponse", targetNamespace = "http://spring:8081/BanqueService", partName = "refundCustomerResponse")
    public RefundCustomerResponse refundCustomer(
        @WebParam(name = "refundCustomerRequest", targetNamespace = "http://spring:8081/BanqueService", partName = "refundCustomerRequest")
        RefundCustomerRequest refundCustomerRequest);

    /**
     * 
     * @param debitCustomerRequest
     * @return
     *     returns com.raps.code.generate.ws.DebitCustomerResponse
     */
    @WebMethod
    @WebResult(name = "debitCustomerResponse", targetNamespace = "http://spring:8081/BanqueService", partName = "debitCustomerResponse")
    public DebitCustomerResponse debitCustomer(
        @WebParam(name = "debitCustomerRequest", targetNamespace = "http://spring:8081/BanqueService", partName = "debitCustomerRequest")
        DebitCustomerRequest debitCustomerRequest);

}
