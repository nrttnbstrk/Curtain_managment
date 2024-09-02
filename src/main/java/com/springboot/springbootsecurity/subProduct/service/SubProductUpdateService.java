package com.springboot.springbootsecurity.subProduct.service;

import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductUpdateRequest;

public interface SubProductUpdateService {

    SubProduct updateProductById(final String productId, final SubProductUpdateRequest subProductUpdateRequest);

}
