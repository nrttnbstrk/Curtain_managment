package com.springboot.springbootsecurity.sale.model.mapper;

import com.springboot.springbootsecurity.common.model.mapper.BaseMapper;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.response.SaleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleToSaleResponseMapper extends BaseMapper<Sale, SaleResponse> {

    @Override
    SaleResponse map(Sale source);

    static SaleToSaleResponseMapper initialize() {
        return Mappers.getMapper(SaleToSaleResponseMapper.class);
    }

}
