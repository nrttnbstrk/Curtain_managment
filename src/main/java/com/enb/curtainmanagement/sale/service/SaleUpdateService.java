package com.enb.curtainmanagement.sale.service;

import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.dto.request.SaleUpdateRequest;

public interface SaleUpdateService {

    Sale updateSaleById(final String saleId, final SaleUpdateRequest saleUpdateRequest);

}
