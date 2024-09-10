package com.enb.curtainmanagement.subProduct.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductCreateRequest;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
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
                .autoBarcode(subProductCreateRequest.getAutoBarcode())
                .amount(subProductCreateRequest.getAmount())
                .supplier(subProductCreateRequest.getSupplier())
                .build();
    }

    static SubProductCreateRequestToSubProductEntityMapper initialize() {
        return Mappers.getMapper(SubProductCreateRequestToSubProductEntityMapper.class);
    }

}
