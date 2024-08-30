package com.springboot.springbootsecurity.sale.model.mapper;

import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleUpdateRequest;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleUpdateRequestToSaleEntityMapper extends BaseMapper<SaleUpdateRequest, SaleEntity> {


    @Named("mapForUpdating")
    default void mapForUpdating(SaleEntity saleEntityToBeUpdate, SaleUpdateRequest saleUpdateRequest) {

        saleEntityToBeUpdate.setCustomerId(saleUpdateRequest.getCustomerId());
        saleEntityToBeUpdate.setProductId(saleUpdateRequest.getProductId());
        saleEntityToBeUpdate.setAmount(saleUpdateRequest.getAmount());
        saleEntityToBeUpdate.setStatus(saleUpdateRequest.getStatus());
    }

    static SaleUpdateRequestToSaleEntityMapper initialize() {
        return Mappers.getMapper(SaleUpdateRequestToSaleEntityMapper.class);
    }

}

