package com.springboot.springbootsecurity.product.model.mapper;

import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.product.model.dto.request.ProductUpdateRequest;
import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductUpdateRequestToProductEntityMapper extends BaseMapper<ProductUpdateRequest, ProductEntity> {


    @Named("mapForUpdating")
    default void mapForUpdating(ProductEntity productEntityToBeUpdate, ProductUpdateRequest productUpdateRequest) {

        productEntityToBeUpdate.setName(productUpdateRequest.getName());
        productEntityToBeUpdate.setUnitType(productUpdateRequest.getUnitType());
        productEntityToBeUpdate.setBrand(productUpdateRequest.getBrand());
        productEntityToBeUpdate.setCode(productUpdateRequest.getCode());
        productEntityToBeUpdate.setBarcode(productEntityToBeUpdate.getBarcode());
        productEntityToBeUpdate.setPurchasePrice(productUpdateRequest.getPurchasePrice());
        productEntityToBeUpdate.setSellingPrice(productUpdateRequest.getSellingPrice());
        productEntityToBeUpdate.setSupplier(productUpdateRequest.getSupplier());
    }

    static ProductUpdateRequestToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductUpdateRequestToProductEntityMapper.class);
    }

}

