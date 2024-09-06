package com.enb.curtainmanagement.customer.service.impl;

import com.enb.curtainmanagement.customer.exception.CustomerNotFoundException;
import com.enb.curtainmanagement.customer.repository.CustomerRepository;
import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
import com.enb.curtainmanagement.customer.service.CustomerDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDeleteServiceImpl implements CustomerDeleteService {

    private final CustomerRepository customerRepository;

    @Override
    public void deleteCustomerById(String customerId) {

        final CustomerEntity customerEntityToBeDelete = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Silmek istediğiniz müşteri mevcut değil."));

        customerRepository.delete(customerEntityToBeDelete);

    }

}
