package com.springboot.springbootsecurity.sale.service;

import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleCreateRequest;

public interface SaleCreateService {

    Sale createSale(final SaleCreateRequest saleCreateRequest);

}
