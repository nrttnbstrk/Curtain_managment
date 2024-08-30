package com.springboot.springbootsecurity.customer.service.impl;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.customer.exception.CustomerNotFoundException;
import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerPagingRequest;
import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import com.springboot.springbootsecurity.customer.model.mapper.ListCustomerEntityToListCustomerMapper;
import com.springboot.springbootsecurity.customer.model.mapper.CustomerEntityToCustomerMapper;
import com.springboot.springbootsecurity.customer.repository.CustomerRepository;
import com.springboot.springbootsecurity.customer.service.CustomerReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerReadServiceImpl implements CustomerReadService {

    private final CustomerRepository customerRepository;

    private final CustomerEntityToCustomerMapper customerEntityToCustomerMapper = CustomerEntityToCustomerMapper.initialize();

    private final ListCustomerEntityToListCustomerMapper listCustomerEntityToListCustomerMapper =
            ListCustomerEntityToListCustomerMapper.initialize();

    @Override
    public Customer getCustomerById(String customerId) {

        final CustomerEntity customerEntityFromDB = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("With given customerId = " + customerId));

        return customerEntityToCustomerMapper.map(customerEntityFromDB);
    }

    @Override
    public CustomPage<Customer> getCustomers(CustomerPagingRequest customerPagingRequest) {

        final Page<CustomerEntity> customerEntityPage = customerRepository.findAll(customerPagingRequest.toPageable());

        if (customerEntityPage.getContent().isEmpty()) {
            throw new CustomerNotFoundException("Couldn't find any customer");
        }

        final List<Customer> customerDomainModels = listCustomerEntityToListCustomerMapper
                .toCustomerList(customerEntityPage.getContent());

        return CustomPage.of(customerDomainModels, customerEntityPage);

    }

}
