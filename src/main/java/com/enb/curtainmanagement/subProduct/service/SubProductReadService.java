package com.enb.curtainmanagement.subProduct.service;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.subProduct.model.SubProduct;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductPagingRequest;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductSearchRequest;
import com.enb.curtainmanagement.subProduct.model.dto.response.SubProductSearchResponse;

import java.util.List;

public interface SubProductReadService {

    SubProduct getProductById(final String productId);

    CustomPage<SubProduct> getProducts(final SubProductPagingRequest subProductPagingRequest);

    List<SubProductSearchResponse> SearchProducts(SubProductSearchRequest searchRequest);
}
