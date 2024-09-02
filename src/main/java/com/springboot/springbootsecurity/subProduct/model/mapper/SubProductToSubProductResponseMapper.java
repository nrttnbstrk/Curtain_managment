package com.springboot.springbootsecurity.subProduct.model.mapper;

import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.dto.response.SubProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubProductToSubProductResponseMapper extends BaseMapper<SubProduct, SubProductResponse> {

    @Override
    SubProductResponse map(SubProduct source);

    static SubProductToSubProductResponseMapper initialize() {
        return Mappers.getMapper(SubProductToSubProductResponseMapper.class);
    }

}
