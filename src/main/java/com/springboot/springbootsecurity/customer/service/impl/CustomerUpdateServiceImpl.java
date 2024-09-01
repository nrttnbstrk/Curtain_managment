package com.springboot.springbootsecurity.customer.service.impl;

import com.springboot.springbootsecurity.customer.exception.CustomerAlreadyExistException;
import com.springboot.springbootsecurity.customer.exception.CustomerNotFoundException;
import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerUpdateRequest;
import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import com.springboot.springbootsecurity.customer.model.mapper.CustomerEntityToCustomerMapper;
import com.springboot.springbootsecurity.customer.model.mapper.CustomerUpdateRequestToCustomerEntityMapper;
import com.springboot.springbootsecurity.customer.repository.CustomerRepository;
import com.springboot.springbootsecurity.customer.service.CustomerUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUpdateServiceImpl implements CustomerUpdateService {

    private final CustomerRepository customerRepository;

    private final CustomerUpdateRequestToCustomerEntityMapper customerUpdateRequestToCustomerEntityMapper =
            CustomerUpdateRequestToCustomerEntityMapper.initialize();

    private final CustomerEntityToCustomerMapper customerEntityToCustomerMapper =
            CustomerEntityToCustomerMapper.initialize();

    @Override
    public Customer updateCustomerById(String customerId, CustomerUpdateRequest customerUpdateRequest) {

        checkCustomerIdNumberUniqueness(customerUpdateRequest.getId_number());

        final CustomerEntity customerEntityToBeUpdate = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("With given customerId = " + customerId));

        customerUpdateRequestToCustomerEntityMapper.mapForUpdating(customerEntityToBeUpdate, customerUpdateRequest);

        CustomerEntity updatedCustomerEntity = customerRepository.save(customerEntityToBeUpdate);

        return customerEntityToCustomerMapper.map(updatedCustomerEntity);

    }

    private void checkCustomerIdNumberUniqueness(final String customerIdNumber) {

    }

}
