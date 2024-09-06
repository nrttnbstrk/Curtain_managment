package com.enb.curtainmanagement.sale.model.mapper;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.common.model.dto.response.CustomPagingResponse;
import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.dto.response.SaleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CustomPageToCustomPagingResponseMapper {

    SaleToSaleResponseMapper CUSTOMER_TO_CUSTOMER_RESPONSE_MAPPER = Mappers.getMapper(SaleToSaleResponseMapper.class);

    default CustomPagingResponse<SaleResponse> toPagingResponse(CustomPage<Sale> salePage) {

        if (salePage == null) {
            return null;
        }

        return CustomPagingResponse.<SaleResponse>builder()
                .content(toSaleResponseList(salePage.getContent()))
                .totalElementCount(salePage.getTotalElementCount())
                .totalPageCount(salePage.getTotalPageCount())
                .pageNumber(salePage.getPageNumber())
                .pageSize(salePage.getPageSize())
                .build();

    }

    default List<SaleResponse> toSaleResponseList(List<Sale> sales) {

        if (sales == null) {
            return null;
        }

        return sales.stream()
                .map(CUSTOMER_TO_CUSTOMER_RESPONSE_MAPPER::map)
                .collect(Collectors.toList());

    }

    static CustomPageToCustomPagingResponseMapper initialize() {
        return Mappers.getMapper(CustomPageToCustomPagingResponseMapper.class);
    }

}
