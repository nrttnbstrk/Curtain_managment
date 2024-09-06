package com.enb.curtainmanagement.sale.service;

import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.dto.request.SaleCreateRequest;

public interface SaleCreateService {

    Sale createSale(final SaleCreateRequest saleCreateRequest);

}
