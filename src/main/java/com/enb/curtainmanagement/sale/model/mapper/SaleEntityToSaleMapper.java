package com.enb.curtainmanagement.sale.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
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

