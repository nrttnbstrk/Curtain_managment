package com.enb.curtainmanagement.product.model.mapper;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.common.model.dto.response.CustomPagingResponse;
import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.model.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CustomPageToCustomPagingResponseMapper {

    ProductToProductResponseMapper productToProductResponseMapper = Mappers.getMapper(ProductToProductResponseMapper.class);

    default CustomPagingResponse<ProductResponse> toPagingResponse(CustomPage<Product> productPage) {

        if (productPage == null) {
            return null;
        }

        return CustomPagingResponse.<ProductResponse>builder()
                .content(toProductResponseList(productPage.getContent()))
                .totalElementCount(productPage.getTotalElementCount())
                .totalPageCount(productPage.getTotalPageCount())
                .pageNumber(productPage.getPageNumber())
                .pageSize(productPage.getPageSize())
                .build();

    }

    default List<ProductResponse> toProductResponseList(List<Product> products) {

        if (products == null) {
            return null;
        }

        return products.stream()
                .map(productToProductResponseMapper::map)
                .collect(Collectors.toList());

    }

    static CustomPageToCustomPagingResponseMapper initialize() {
        return Mappers.getMapper(CustomPageToCustomPagingResponseMapper.class);
    }

}
