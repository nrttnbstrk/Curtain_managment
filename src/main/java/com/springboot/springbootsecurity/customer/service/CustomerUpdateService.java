package com.springboot.springbootsecurity.customer.service;

import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerUpdateRequest;

public interface CustomerUpdateService {

    Customer updateCustomerById(final String customerId, final CustomerUpdateRequest customerUpdateRequest);

}
