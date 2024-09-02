package com.springboot.springbootsecurity.subProduct.service;

import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductCreateRequest;

public interface SubProductCreateService {

    SubProduct createProduct(final SubProductCreateRequest subProductCreateRequest);

}
