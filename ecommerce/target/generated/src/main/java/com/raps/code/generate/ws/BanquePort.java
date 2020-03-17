
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
@WebService(name = "BanquePort", targetNamespace = "http://localhost:8080/BanqueService")
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
    @WebResult(name = "refundCustomerResponse", targetNamespace = "http://localhost:8080/BanqueService", partName = "refundCustomerResponse")
    public RefundCustomerResponse refundCustomer(
        @WebParam(name = "refundCustomerRequest", targetNamespace = "http://localhost:8080/BanqueService", partName = "refundCustomerRequest")
        RefundCustomerRequest refundCustomerRequest);

}