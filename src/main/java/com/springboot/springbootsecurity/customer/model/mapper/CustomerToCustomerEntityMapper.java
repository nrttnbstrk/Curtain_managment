package com.springboot.springbootsecurity.customer.model.mapper;


import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerToCustomerEntityMapper extends BaseMapper<Customer, CustomerEntity> {

    @Override
    CustomerEntity map(Customer source);

    static CustomerToCustomerEntityMapper initialize() {
        return Mappers.getMapper(CustomerToCustomerEntityMapper.class);
    }

}

