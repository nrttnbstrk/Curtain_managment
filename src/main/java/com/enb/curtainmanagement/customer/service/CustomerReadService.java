package com.enb.curtainmanagement.customer.service;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerPagingRequest;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerSearchRequest;
import com.enb.curtainmanagement.customer.model.dto.response.CustomerSearchResponse;

import java.util.List;

public interface CustomerReadService {

    Customer getCustomerById(final String productId);

    CustomPage<Customer> getCustomers(final CustomerPagingRequest customerPagingRequest);

    List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest searchRequest);
}
