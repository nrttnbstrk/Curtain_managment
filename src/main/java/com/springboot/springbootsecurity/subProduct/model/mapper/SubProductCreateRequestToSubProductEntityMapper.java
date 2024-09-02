package com.springboot.springbootsecurity.subProduct.model.mapper;

import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductCreateRequest;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubProductCreateRequestToSubProductEntityMapper extends BaseMapper<SubProductCreateRequest, SubProductEntity> {

    @Named("mapForSaving")
    default SubProductEntity mapForSaving(SubProductCreateRequest subProductCreateRequest) {
        return SubProductEntity.builder()
                .productId(subProductCreateRequest.getProductId())
                .barcode(subProductCreateRequest.getBarcode())
                .amount(subProductCreateRequest.getAmount())
                .supplier(subProductCreateRequest.getSupplier())
                .build();
    }

    static SubProductCreateRequestToSubProductEntityMapper initialize() {
        return Mappers.getMapper(SubProductCreateRequestToSubProductEntityMapper.class);
    }

}
