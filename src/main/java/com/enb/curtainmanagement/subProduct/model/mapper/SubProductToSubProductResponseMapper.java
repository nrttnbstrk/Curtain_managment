package com.enb.curtainmanagement.subProduct.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.subProduct.model.SubProduct;
import com.enb.curtainmanagement.subProduct.model.dto.response.SubProductResponse;
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
