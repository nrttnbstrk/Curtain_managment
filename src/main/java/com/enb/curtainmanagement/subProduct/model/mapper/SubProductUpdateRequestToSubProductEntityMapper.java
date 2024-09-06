package com.enb.curtainmanagement.subProduct.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductUpdateRequest;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubProductUpdateRequestToSubProductEntityMapper extends BaseMapper<SubProductUpdateRequest, SubProductEntity> {


    @Named("mapForUpdating")
    default void mapForUpdating(SubProductEntity subProductEntityToBeUpdate, SubProductUpdateRequest subProductUpdateRequest) {
        subProductEntityToBeUpdate.setProductId(subProductEntityToBeUpdate.getProductId());
        subProductEntityToBeUpdate.setBarcode(subProductEntityToBeUpdate.getBarcode());
        subProductEntityToBeUpdate.setSupplier(subProductUpdateRequest.getSupplier());
        subProductEntityToBeUpdate.setAmount(subProductUpdateRequest.getAmount());
    }


    static SubProductUpdateRequestToSubProductEntityMapper initialize() {
        return Mappers.getMapper(SubProductUpdateRequestToSubProductEntityMapper.class);
    }

}

