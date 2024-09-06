package com.enb.curtainmanagement.sale.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
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

