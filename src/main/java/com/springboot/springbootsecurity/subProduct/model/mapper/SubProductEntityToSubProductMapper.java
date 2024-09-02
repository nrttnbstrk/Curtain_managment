package com.springboot.springbootsecurity.subProduct.model.mapper;


import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubProductEntityToSubProductMapper extends BaseMapper<SubProductEntity, SubProduct> {

    @Override
    SubProduct map(SubProductEntity source);

    static SubProductEntityToSubProductMapper initialize() {
        return Mappers.getMapper(SubProductEntityToSubProductMapper.class);
    }

}

