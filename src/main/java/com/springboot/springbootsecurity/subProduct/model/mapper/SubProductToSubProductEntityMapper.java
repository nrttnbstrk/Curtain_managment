package com.springboot.springbootsecurity.subProduct.model.mapper;


import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubProductToSubProductEntityMapper extends BaseMapper<SubProduct, SubProductEntity> {

    @Override
    SubProductEntity map(SubProduct source);

    static SubProductToSubProductEntityMapper initialize() {
        return Mappers.getMapper(SubProductToSubProductEntityMapper.class);
    }

}

