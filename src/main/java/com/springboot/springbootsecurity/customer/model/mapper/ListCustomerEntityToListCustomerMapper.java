package com.springboot.springbootsecurity.customer.model.mapper;

import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ListCustomerEntityToListCustomerMapper {

    CustomerEntityToCustomerMapper CUSTOMER_ENTITY_TO_CUSTOMER_MAPPER = Mappers.getMapper(CustomerEntityToCustomerMapper.class);

    default List<Customer> toCustomerList(List<CustomerEntity> customerEntities) {

        if (customerEntities == null) {
            return null;
        }

        return customerEntities.stream()
                .map(CUSTOMER_ENTITY_TO_CUSTOMER_MAPPER::map)
                .collect(Collectors.toList());

    }


    static ListCustomerEntityToListCustomerMapper initialize() {
        return Mappers.getMapper(ListCustomerEntityToListCustomerMapper.class);
    }

}
