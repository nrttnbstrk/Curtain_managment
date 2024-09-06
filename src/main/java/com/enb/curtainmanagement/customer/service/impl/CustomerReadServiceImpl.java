package com.enb.curtainmanagement.customer.service.impl;

import com.enb.curtainmanagement.customer.exception.CustomerNotFoundException;
import com.enb.curtainmanagement.customer.repository.CustomerRepository;
import com.enb.curtainmanagement.customer.specification.CustomerSpecification;
import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerPagingRequest;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerSearchRequest;
import com.enb.curtainmanagement.customer.model.dto.response.CustomerSearchResponse;
import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
import com.enb.curtainmanagement.customer.model.mapper.ListCustomerEntityToListCustomerMapper;
import com.enb.curtainmanagement.customer.model.mapper.CustomerEntityToCustomerMapper;
import com.enb.curtainmanagement.customer.service.CustomerReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerReadServiceImpl implements CustomerReadService {

    private final CustomerRepository customerRepository;
    public boolean doesCustomerExistByIdNumber(String idNumber) {
        return customerRepository.existsCustomerEntityByIdNumber(idNumber);
    }
    private final CustomerEntityToCustomerMapper customerEntityToCustomerMapper = CustomerEntityToCustomerMapper.initialize();

    private final ListCustomerEntityToListCustomerMapper listCustomerEntityToListCustomerMapper =
            ListCustomerEntityToListCustomerMapper.initialize();

    @Override
    public Customer getCustomerById(String customerId) {

        final CustomerEntity customerEntityFromDB = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Belirtilen müşteri mevcut değil."));

        return customerEntityToCustomerMapper.map(customerEntityFromDB);
    }

    @Override
    public CustomPage<Customer> getCustomers(CustomerPagingRequest customerPagingRequest) {

        final Page<CustomerEntity> customerEntityPage = customerRepository.findAll(customerPagingRequest.toPageable());

        if (customerEntityPage.getContent().isEmpty()) {
            throw new CustomerNotFoundException("Herhangi bir müşteri bulunamadı.");
        }

        final List<Customer> customerDomainModels = listCustomerEntityToListCustomerMapper
                .toCustomerList(customerEntityPage.getContent());

        return CustomPage.of(customerDomainModels, customerEntityPage);

    }
    @Override
    public List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest searchRequest) {
        Specification<CustomerEntity> specification = Specification.where(null);

        if (searchRequest.getFirstname() != null && !searchRequest.getFirstname().isEmpty()) {
            specification = specification.and(CustomerSpecification.hasFirstname(searchRequest.getFirstname()));
        }
        if (searchRequest.getLastname() != null && !searchRequest.getLastname().isEmpty()) {
            specification = specification.and(CustomerSpecification.hasLastname(searchRequest.getLastname()));
        }
        if (searchRequest.getId() != null && !searchRequest.getId().isEmpty()) {
            specification = specification.and(CustomerSpecification.hasId(searchRequest.getId()));
        }
        if (searchRequest.getPhone() != null && !searchRequest.getPhone().isEmpty()) {
            specification = specification.and(CustomerSpecification.hasPhone(searchRequest.getPhone()));
        }
        if (searchRequest.getAddress() != null && !searchRequest.getAddress().isEmpty()) {
            specification = specification.and(CustomerSpecification.hasAddress(searchRequest.getAddress()));
        }
        if (searchRequest.getLast_id() != null && !searchRequest.getLast_id().isEmpty()) {
            specification = specification.and(CustomerSpecification.hasLast_id(searchRequest.getLast_id()));
        }
        List<CustomerEntity> customerEntities = customerRepository.findAll(specification);

        return customerEntities.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private CustomerSearchResponse mapToResponse(CustomerEntity entity) {
        CustomerSearchResponse response = new CustomerSearchResponse();
        response.setFirstname(entity.getFirstname());
        response.setLastname(entity.getLastname());
        response.setId(entity.getId());
        response.setLast_Id(entity.getLast_id());
        response.setAddress(entity.getAddress());
        response.setPhone(entity.getPhone());
        return response;
    }
}
