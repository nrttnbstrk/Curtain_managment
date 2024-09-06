package com.enb.curtainmanagement.customer.service;

import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerUpdateRequest;

public interface CustomerUpdateService {

    Customer updateCustomerById(final String customerId, final CustomerUpdateRequest customerUpdateRequest);

}
