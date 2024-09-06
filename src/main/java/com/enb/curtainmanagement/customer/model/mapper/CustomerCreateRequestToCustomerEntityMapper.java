package com.enb.curtainmanagement.customer.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.customer.model.dto.request.CustomerCreateRequest;
import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerCreateRequestToCustomerEntityMapper extends BaseMapper<CustomerCreateRequest, CustomerEntity> {

    @Named("mapForSaving")
    default CustomerEntity mapForSaving(CustomerCreateRequest customerCreateRequest) {
        return CustomerEntity.builder()
                .firstname(customerCreateRequest.getFirstname())
                .lastname(customerCreateRequest.getLastname())
                .last_id(customerCreateRequest.getLast_id())
                .idNumber(customerCreateRequest.getIdNumber())
                .phone(customerCreateRequest.getPhone())
                .city(customerCreateRequest.getCity())
                .district(customerCreateRequest.getDistrict())
                .neighborhood(customerCreateRequest.getNeighborhood())
                .address(customerCreateRequest.getAddress())
                .detail(customerCreateRequest.getDetail())
                .build();
    }


    static CustomerCreateRequestToCustomerEntityMapper initialize() {
        return Mappers.getMapper(CustomerCreateRequestToCustomerEntityMapper.class);
    }

}
