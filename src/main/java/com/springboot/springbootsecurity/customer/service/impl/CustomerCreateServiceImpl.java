package com.springboot.springbootsecurity.customer.service.impl;

import com.springboot.springbootsecurity.customer.exception.CustomerAlreadyExistException;
import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerCreateRequest;
import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import com.springboot.springbootsecurity.customer.model.mapper.CustomerCreateRequestToCustomerEntityMapper;
import com.springboot.springbootsecurity.customer.model.mapper.CustomerEntityToCustomerMapper;
import com.springboot.springbootsecurity.customer.repository.CustomerRepository;
import com.springboot.springbootsecurity.customer.service.CustomerCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreateServiceImpl implements CustomerCreateService {

    private final CustomerRepository customerRepository;

    private final CustomerCreateRequestToCustomerEntityMapper customerCreateRequestToCustomerEntityMapper =
            CustomerCreateRequestToCustomerEntityMapper.initialize();

    private final CustomerEntityToCustomerMapper customerEntityToCustomerMapper = CustomerEntityToCustomerMapper.initialize();

    @Override
    public Customer createCustomer(CustomerCreateRequest customerCreateRequest) {

        checkUniquenessIdNumber(customerCreateRequest.getIdNumber());

        final CustomerEntity customerEntityToBeSave = customerCreateRequestToCustomerEntityMapper.mapForSaving(customerCreateRequest);

        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntityToBeSave);

        return customerEntityToCustomerMapper.map(savedCustomerEntity);

    }

    private void checkUniquenessIdNumber(final String idNumber) {
        if (customerRepository.existsCustomerEntityByIdNumber(idNumber)) {
            throw new CustomerAlreadyExistException("Bu Kimlik numarası ile müşteri zaten mevcut.");
        }
    }

}
