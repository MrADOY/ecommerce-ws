package com.banque.banque.soap;

import localhost._8080.banqueservice.RefundCustomerRequest;
import localhost._8080.banqueservice.RefundCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BanqueEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/BanqueService";

    private BanqueRepository banqueRepository;

    @Autowired
    public BanqueEndpoint(BanqueRepository banqueRepository) {
        this.banqueRepository = banqueRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "refundCustomerRequest")
    @ResponsePayload
        public RefundCustomerResponse refundCustomer(@RequestPayload RefundCustomerRequest cardNumber) {
        RefundCustomerResponse response = new RefundCustomerResponse();
        response.setStatus(banqueRepository.refundCustomer(cardNumber.getCardNumber()));
        return response;
    }
}
