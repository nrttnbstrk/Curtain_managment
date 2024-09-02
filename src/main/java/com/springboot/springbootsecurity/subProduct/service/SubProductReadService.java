package com.springboot.springbootsecurity.subProduct.service;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductPagingRequest;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductSearchRequest;
import com.springboot.springbootsecurity.subProduct.model.dto.response.SubProductSearchResponse;

import java.util.List;

public interface SubProductReadService {

    SubProduct getProductById(final String productId);

    CustomPage<SubProduct> getProducts(final SubProductPagingRequest subProductPagingRequest);

    List<SubProductSearchResponse> SearchProducts(SubProductSearchRequest searchRequest);
}
