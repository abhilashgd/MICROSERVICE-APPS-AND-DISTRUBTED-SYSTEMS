package com.abhilashgd.customer;

import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @author: abhilashgd
 * @since: 2/9/22
 */
@Data
@Builder
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
