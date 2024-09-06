package com.enb.curtainmanagement.product.service;

import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.model.dto.request.ProductCreateRequest;

public interface ProductCreateService {

    Product createProduct(final ProductCreateRequest productCreateRequest);

}
