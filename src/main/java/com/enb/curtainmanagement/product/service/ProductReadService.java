package com.enb.curtainmanagement.product.service;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.model.dto.request.ProductPagingRequest;
import com.enb.curtainmanagement.product.model.dto.request.ProductSearchRequest;
import com.enb.curtainmanagement.product.model.dto.response.ProductSearchResponse;

import java.util.List;

public interface ProductReadService {

    Product getProductById(final String productId);

    CustomPage<Product> getProducts(final ProductPagingRequest productPagingRequest);

    List<ProductSearchResponse> SearchProducts(ProductSearchRequest searchRequest);
}
