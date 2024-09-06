package com.enb.curtainmanagement.subProduct.service;

import com.enb.curtainmanagement.subProduct.model.SubProduct;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductCreateRequest;

public interface SubProductCreateService {

    SubProduct createProduct(final SubProductCreateRequest subProductCreateRequest);

}
