package com.enb.curtainmanagement.product.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductEntityToProductMapper extends BaseMapper<ProductEntity, Product> {

    @Override
    Product map(ProductEntity source);

    static ProductEntityToProductMapper initialize() {
        return Mappers.getMapper(ProductEntityToProductMapper.class);
    }

}

