package com.springboot.springbootsecurity.customer.service.impl;

import com.springboot.springbootsecurity.customer.exception.CustomerNotFoundException;
import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import com.springboot.springbootsecurity.customer.repository.CustomerRepository;
import com.springboot.springbootsecurity.customer.service.CustomerDeleteService;
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
