package com.enb.curtainmanagement.customer.controller;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.common.model.dto.response.CustomPagingResponse;
import com.enb.curtainmanagement.common.model.dto.response.CustomResponse;
import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerCreateRequest;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerPagingRequest;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerSearchRequest;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerUpdateRequest;
import com.enb.curtainmanagement.customer.model.dto.response.CustomerResponse;
import com.enb.curtainmanagement.customer.model.dto.response.CustomerSearchResponse;
import com.enb.curtainmanagement.customer.model.mapper.CustomPageToCustomPagingResponseMapper;
import com.enb.curtainmanagement.customer.model.mapper.CustomerToCustomerResponseMapper;
import com.enb.curtainmanagement.customer.service.CustomerCreateService;
import com.enb.curtainmanagement.customer.service.CustomerDeleteService;
import com.enb.curtainmanagement.customer.service.CustomerReadService;
import com.enb.curtainmanagement.customer.service.CustomerUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerCreateService customerCreateService;
    private final CustomerReadService customerReadService;
    private final CustomerUpdateService customerUpdateService;
    private final CustomerDeleteService customerDeleteService;

    private final CustomerToCustomerResponseMapper customerToCustomerResponseMapper = CustomerToCustomerResponseMapper.initialize();

    private final CustomPageToCustomPagingResponseMapper customPageToCustomPagingResponseMapper =
            CustomPageToCustomPagingResponseMapper.initialize();

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<String> createCustomer(@RequestBody @Valid final CustomerCreateRequest customerCreateRequest) {

        final Customer createdCustomer = customerCreateService
                .createCustomer(customerCreateRequest);

        return CustomResponse.successOf(createdCustomer.getId());
    }

    @GetMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<CustomerResponse> getProductById(@PathVariable @UUID final String customerId) {

        final Customer customer = customerReadService.getCustomerById(customerId);

        final CustomerResponse customerResponse = customerToCustomerResponseMapper.map(customer);

        return CustomResponse.successOf(customerResponse);

    }

    @PostMapping("/allCustomer")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<CustomerResponse>> getCustomers(
            @RequestBody @Valid final CustomerPagingRequest customerPagingRequest) {

        final CustomPage<Customer> customerPage = customerReadService.getCustomers(customerPagingRequest);

        final CustomPagingResponse<CustomerResponse> customerPagingResponse =
                customPageToCustomPagingResponseMapper.toPagingResponse(customerPage);

        return CustomResponse.successOf(customerPagingResponse);
    }

    @PutMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<CustomerResponse> updatedCustomerById(
            @RequestBody @Valid final CustomerUpdateRequest customerUpdateRequest,
            @PathVariable @UUID final String customerId) {

        final Customer updatedCustomer = customerUpdateService.updateCustomerById(customerId, customerUpdateRequest);

        final CustomerResponse customerResponse = customerToCustomerResponseMapper.map(updatedCustomer);

        return CustomResponse.successOf(customerResponse);
    }

    @DeleteMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<Void> deleteProductById(@PathVariable @UUID final String customerId) {

        customerDeleteService.deleteCustomerById(customerId);
        return CustomResponse.SUCCESS;
    }
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<CustomerSearchResponse> searchCustomers(@RequestBody @Valid final CustomerSearchRequest searchRequest) {
        return customerReadService.searchCustomers(searchRequest);
    }
}
