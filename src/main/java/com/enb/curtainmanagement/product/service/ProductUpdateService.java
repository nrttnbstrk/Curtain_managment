package com.enb.curtainmanagement.product.service;

import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.model.dto.request.ProductUpdateRequest;

public interface ProductUpdateService {

    Product updateProductById(final String productId, final ProductUpdateRequest productUpdateRequest);

}
