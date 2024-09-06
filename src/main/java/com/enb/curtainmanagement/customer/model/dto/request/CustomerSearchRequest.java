package com.enb.curtainmanagement.customer.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchRequest {
    private String firstname;
    private String lastname;
    private String last_id;
    private String id;
    private String phone;
    private String address;
}
