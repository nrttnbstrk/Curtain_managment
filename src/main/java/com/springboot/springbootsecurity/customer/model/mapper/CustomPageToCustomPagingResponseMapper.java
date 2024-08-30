package com.springboot.springbootsecurity.customer.model.mapper;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.common.model.dto.response.CustomPagingResponse;
import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.dto.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CustomPageToCustomPagingResponseMapper {

    CustomerToCustomerResponseMapper CUSTOMER_TO_CUSTOMER_RESPONSE_MAPPER = Mappers.getMapper(CustomerToCustomerResponseMapper.class);

    default CustomPagingResponse<CustomerResponse> toPagingResponse(CustomPage<Customer> customerPage) {

        if (customerPage == null) {
            return null;
        }

        return CustomPagingResponse.<CustomerResponse>builder()
                .content(toCustomerResponseList(customerPage.getContent()))
                .totalElementCount(customerPage.getTotalElementCount())
                .totalPageCount(customerPage.getTotalPageCount())
                .pageNumber(customerPage.getPageNumber())
                .pageSize(customerPage.getPageSize())
                .build();

    }

    default List<CustomerResponse> toCustomerResponseList(List<Customer> customers) {

        if (customers == null) {
            return null;
        }

        return customers.stream()
                .map(CUSTOMER_TO_CUSTOMER_RESPONSE_MAPPER::map)
                .collect(Collectors.toList());

    }

    static CustomPageToCustomPagingResponseMapper initialize() {
        return Mappers.getMapper(CustomPageToCustomPagingResponseMapper.class);
    }

}
