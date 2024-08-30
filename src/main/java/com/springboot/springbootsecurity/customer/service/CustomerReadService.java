package com.springboot.springbootsecurity.customer.service;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerPagingRequest;

public interface CustomerReadService {

    Customer getCustomerById(final String productId);

    CustomPage<Customer> getCustomers(final CustomerPagingRequest customerPagingRequest);

}
