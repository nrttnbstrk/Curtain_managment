package com.springboot.springbootsecurity.customer.service;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerPagingRequest;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerSearchRequest;
import com.springboot.springbootsecurity.customer.model.dto.response.CustomerSearchResponse;

import java.util.List;

public interface CustomerReadService {

    Customer getCustomerById(final String productId);

    CustomPage<Customer> getCustomers(final CustomerPagingRequest customerPagingRequest);

    List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest searchRequest);
}
