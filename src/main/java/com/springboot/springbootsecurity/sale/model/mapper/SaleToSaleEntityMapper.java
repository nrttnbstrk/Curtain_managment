package com.springboot.springbootsecurity.sale.model.mapper;


import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleToSaleEntityMapper extends BaseMapper<Sale, SaleEntity> {

    @Override
    SaleEntity map(Sale source);

    static SaleToSaleEntityMapper initialize() {
        return Mappers.getMapper(SaleToSaleEntityMapper.class);
    }

}

