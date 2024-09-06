package com.enb.curtainmanagement.subProduct.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.subProduct.model.SubProduct;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
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

