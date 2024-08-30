package com.springboot.springbootsecurity.sale.model.mapper;


import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleEntityToSaleMapper extends BaseMapper<SaleEntity, Sale> {

    @Override
    Sale map(SaleEntity source);

    static SaleEntityToSaleMapper initialize() {
        return Mappers.getMapper(SaleEntityToSaleMapper.class);
    }

}

