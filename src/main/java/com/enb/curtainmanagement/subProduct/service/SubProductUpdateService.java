package com.enb.curtainmanagement.subProduct.service;

import com.enb.curtainmanagement.subProduct.model.SubProduct;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductUpdateRequest;

public interface SubProductUpdateService {

    SubProduct updateProductById(final String productId, final SubProductUpdateRequest subProductUpdateRequest);

}
