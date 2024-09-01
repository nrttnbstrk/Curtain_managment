package com.springboot.springbootsecurity.product.service;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.product.model.Product;
import com.springboot.springbootsecurity.product.model.dto.request.ProductPagingRequest;
import com.springboot.springbootsecurity.product.model.dto.request.ProductSearchRequest;
import com.springboot.springbootsecurity.product.model.dto.response.ProductSearchResponse;

import java.util.List;

public interface ProductReadService {

    Product getProductById(final String productId);

    CustomPage<Product> getProducts(final ProductPagingRequest productPagingRequest);

    List<ProductSearchResponse> SearchProducts(ProductSearchRequest searchRequest);
}
