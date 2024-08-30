package com.springboot.springbootsecurity.customer.model.mapper;

import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.customer.model.dto.request.CustomerUpdateRequest;
import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerUpdateRequestToCustomerEntityMapper extends BaseMapper<CustomerUpdateRequest, CustomerEntity> {


    @Named("mapForUpdating")
    default void mapForUpdating(CustomerEntity customerEntityToBeUpdate, CustomerUpdateRequest customerUpdateRequest) {

        customerEntityToBeUpdate.setFirstname(customerUpdateRequest.getFirstname());
        customerEntityToBeUpdate.setLastname(customerUpdateRequest.getLastname());
        customerEntityToBeUpdate.setLast_id(customerEntityToBeUpdate.getLast_id());
        customerEntityToBeUpdate.setId_number(customerUpdateRequest.getId_number());
        customerEntityToBeUpdate.setPhone(customerUpdateRequest.getPhone());
        customerEntityToBeUpdate.setCity(customerUpdateRequest.getCity());
        customerEntityToBeUpdate.setDistrict(customerUpdateRequest.getDistrict());
        customerEntityToBeUpdate.setNeighborhood(customerUpdateRequest.getNeighborhood());
        customerEntityToBeUpdate.setAddress(customerUpdateRequest.getAddress());
        customerEntityToBeUpdate.setDetail(customerEntityToBeUpdate.getDetail());
    }

    static CustomerUpdateRequestToCustomerEntityMapper initialize() {
        return Mappers.getMapper(CustomerUpdateRequestToCustomerEntityMapper.class);
    }

}

