package com.enb.curtainmanagement.customer.service;

import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerCreateRequest;

public interface CustomerCreateService {

    Customer createCustomer(final CustomerCreateRequest customerCreateRequest);

}
