package com.springboot.springbootsecurity.sale.model.mapper;

import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleCreateRequest;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import jakarta.validation.constraints.NotBlank;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleCreateRequestToSaleEntityMapper extends BaseMapper<SaleCreateRequest, SaleEntity> {

    @Named("mapForSaving")
    default SaleEntity mapForSaving(SaleCreateRequest saleCreateRequest) {
        return SaleEntity.builder()
                .customerId(saleCreateRequest.getCustomerId())
                .productId(saleCreateRequest.getProductId())
                .amount(saleCreateRequest.getAmount())
                .status(saleCreateRequest.getStatus())
                .build();
    }


    static SaleCreateRequestToSaleEntityMapper initialize() {
        return Mappers.getMapper(SaleCreateRequestToSaleEntityMapper.class);
    }

}
