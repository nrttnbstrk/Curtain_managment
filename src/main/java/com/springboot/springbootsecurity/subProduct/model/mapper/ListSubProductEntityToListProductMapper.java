package com.springboot.springbootsecurity.subProduct.model.mapper;

import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ListSubProductEntityToListProductMapper {

    SubProductEntityToSubProductMapper SUB_PRODUCT_ENTITY_TO_SUB_PRODUCT_MAPPER = Mappers.getMapper(SubProductEntityToSubProductMapper.class);

    default List<SubProduct> toProductList(List<SubProductEntity> productEntities) {

        if (productEntities == null) {
            return null;
        }

        return productEntities.stream()
                .map(SUB_PRODUCT_ENTITY_TO_SUB_PRODUCT_MAPPER::map)
                .collect(Collectors.toList());

    }


    static ListSubProductEntityToListProductMapper initialize() {
        return Mappers.getMapper(ListSubProductEntityToListProductMapper.class);
    }

}
