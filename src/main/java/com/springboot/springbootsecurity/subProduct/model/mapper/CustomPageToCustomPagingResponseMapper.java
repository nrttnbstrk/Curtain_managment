package com.springboot.springbootsecurity.subProduct.model.mapper;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.common.model.dto.response.CustomPagingResponse;
import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.dto.response.SubProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CustomPageToCustomPagingResponseMapper {

    SubProductToSubProductResponseMapper SUB_PRODUCT_TO_SUB_PRODUCT_RESPONSE_MAPPER = Mappers.getMapper(SubProductToSubProductResponseMapper.class);

    default CustomPagingResponse<SubProductResponse> toPagingResponse(CustomPage<SubProduct> productPage) {

        if (productPage == null) {
            return null;
        }

        return CustomPagingResponse.<SubProductResponse>builder()
                .content(toProductResponseList(productPage.getContent()))
                .totalElementCount(productPage.getTotalElementCount())
                .totalPageCount(productPage.getTotalPageCount())
                .pageNumber(productPage.getPageNumber())
                .pageSize(productPage.getPageSize())
                .build();

    }

    default List<SubProductResponse> toProductResponseList(List<SubProduct> products) {

        if (products == null) {
            return null;
        }

        return products.stream()
                .map(SUB_PRODUCT_TO_SUB_PRODUCT_RESPONSE_MAPPER::map)
                .collect(Collectors.toList());

    }

    static CustomPageToCustomPagingResponseMapper initialize() {
        return Mappers.getMapper(CustomPageToCustomPagingResponseMapper.class);
    }

}
