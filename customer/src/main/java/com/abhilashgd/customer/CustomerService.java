package com.abhilashgd.customer;

import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: abhilashgd
 * @since: 2/9/22
 */
@Service
public record CustomerService() {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer =Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //TODO: check if email is valid
        //TODO: check if email not taken
        //TODO: store customer in db
    }
}
