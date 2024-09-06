package com.enb.curtainmanagement.customer.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
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

