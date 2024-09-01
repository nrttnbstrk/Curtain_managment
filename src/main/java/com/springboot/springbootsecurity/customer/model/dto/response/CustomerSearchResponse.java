package com.springboot.springbootsecurity.customer.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchResponse {
    private String firstname;
    private String lastname;
    private String id;
    private String last_Id;
    private String phone;
    private String address;
}
