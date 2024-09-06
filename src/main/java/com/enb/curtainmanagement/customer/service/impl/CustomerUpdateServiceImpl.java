package com.enb.curtainmanagement.customer.service.impl;

import com.enb.curtainmanagement.customer.exception.CustomerAlreadyExistException;
import com.enb.curtainmanagement.customer.exception.CustomerNotFoundException;
import com.enb.curtainmanagement.customer.repository.CustomerRepository;
import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerUpdateRequest;
import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
import com.enb.curtainmanagement.customer.model.mapper.CustomerEntityToCustomerMapper;
import com.enb.curtainmanagement.customer.model.mapper.CustomerUpdateRequestToCustomerEntityMapper;
import com.enb.curtainmanagement.customer.service.CustomerUpdateService;
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

        checkCustomerIdNumberUniqueness(customerUpdateRequest.getIdNumber());

        final CustomerEntity customerEntityToBeUpdate = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Belirtilen müşteri mevcut değil." ));

        customerUpdateRequestToCustomerEntityMapper.mapForUpdating(customerEntityToBeUpdate, customerUpdateRequest);

        CustomerEntity updatedCustomerEntity = customerRepository.save(customerEntityToBeUpdate);

        return customerEntityToCustomerMapper.map(updatedCustomerEntity);

    }

    private void checkCustomerIdNumberUniqueness(final String idNumber) {
        if (customerRepository.existsCustomerEntityByIdNumber(idNumber)) {
            throw new CustomerAlreadyExistException("Bu Kimlik numarası ile müşteri zaten mevcut.");
        }
    }

}
