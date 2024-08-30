package com.springboot.springbootsecurity.sale.service;

import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleUpdateRequest;

public interface SaleUpdateService {

    Sale updateSaleById(final String saleId, final SaleUpdateRequest saleUpdateRequest);

}
