package com.banque.banque.soap;

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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "refundCustomer")
    @ResponsePayload
    public boolean refundCustomer(@RequestPayload String cardNumber) {
        return banqueRepository.findCountry(cardNumber);
    }
}
