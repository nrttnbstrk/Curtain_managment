package com.enb.curtainmanagement.sale.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.dto.response.SaleResponse;
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
