package com.springboot.springbootsecurity.customer.model.mapper;


import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.customer.model.Customer;
import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerEntityToCustomerMapper extends BaseMapper<CustomerEntity, Customer> {

    @Override
    Customer map(CustomerEntity source);

    static CustomerEntityToCustomerMapper initialize() {
        return Mappers.getMapper(CustomerEntityToCustomerMapper.class);
    }

}

