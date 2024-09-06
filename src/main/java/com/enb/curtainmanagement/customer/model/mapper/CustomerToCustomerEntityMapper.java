package com.enb.curtainmanagement.customer.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
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

