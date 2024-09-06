package com.enb.curtainmanagement.subProduct.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.subProduct.model.SubProduct;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
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

