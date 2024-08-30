package com.springboot.springbootsecurity.customer.service;

import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerCreateRequest;

public interface CustomerCreateService {

    Customer createCustomer(final CustomerCreateRequest customerCreateRequest);

}
