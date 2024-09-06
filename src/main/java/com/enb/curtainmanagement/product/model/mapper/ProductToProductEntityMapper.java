package com.enb.curtainmanagement.product.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductToProductEntityMapper extends BaseMapper<Product, ProductEntity> {

    @Override
    ProductEntity map(Product source);

    static ProductToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductToProductEntityMapper.class);
    }

}

