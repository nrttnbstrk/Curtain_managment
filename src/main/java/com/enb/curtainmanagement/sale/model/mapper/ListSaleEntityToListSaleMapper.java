package com.enb.curtainmanagement.sale.model.mapper;

import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ListSaleEntityToListSaleMapper {

    SaleEntityToSaleMapper CUSTOMER_ENTITY_TO_CUSTOMER_MAPPER = Mappers.getMapper(SaleEntityToSaleMapper.class);

    default List<Sale> toSaleList(List<SaleEntity> saleEntities) {

        if (saleEntities == null) {
            return null;
        }

        return saleEntities.stream()
                .map(CUSTOMER_ENTITY_TO_CUSTOMER_MAPPER::map)
                .collect(Collectors.toList());

    }


    static ListSaleEntityToListSaleMapper initialize() {
        return Mappers.getMapper(ListSaleEntityToListSaleMapper.class);
    }

}
