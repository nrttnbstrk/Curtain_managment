package com.enb.curtainmanagement.customer.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.customer.model.Customer;
import com.enb.curtainmanagement.customer.model.dto.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerToCustomerResponseMapper extends BaseMapper<Customer, CustomerResponse> {

    @Override
    CustomerResponse map(Customer source);

    static CustomerToCustomerResponseMapper initialize() {
        return Mappers.getMapper(CustomerToCustomerResponseMapper.class);
    }

}
