package com.abhilashgd.customer;

/**
 * @version 1.0
 * @author: abhilashgd
 * @since: 2/9/22
 */
public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
