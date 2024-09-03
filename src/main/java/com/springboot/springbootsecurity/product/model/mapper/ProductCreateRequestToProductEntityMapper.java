package com.springboot.springbootsecurity.product.model.mapper;

import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.product.model.dto.request.ProductCreateRequest;
import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCreateRequestToProductEntityMapper extends BaseMapper<ProductCreateRequest, ProductEntity> {

    @Named("mapForSaving")
    default ProductEntity mapForSaving(ProductCreateRequest productCreateRequest) {
        return ProductEntity.builder()
                .name(productCreateRequest.getName())
                .brand(productCreateRequest.getBrand())
                .code(productCreateRequest.getCode())
                .barcode(productCreateRequest.getBarcode())
                .unitType(productCreateRequest.getUnitType())
                .purchasePrice(productCreateRequest.getPurchasePrice())
                .sellingPrice(productCreateRequest.getSellingPrice())
                .supplier(productCreateRequest.getSupplier())
                .build();
    }

    static ProductCreateRequestToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductCreateRequestToProductEntityMapper.class);
    }

}
