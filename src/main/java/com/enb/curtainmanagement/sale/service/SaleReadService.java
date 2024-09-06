package com.enb.curtainmanagement.sale.service;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.sale.model.dto.request.SaleSearchRequest;
import com.enb.curtainmanagement.sale.model.dto.response.SaleSearchResponse;
import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.dto.request.SalePagingRequest;

import java.util.List;
public interface SaleReadService {

    Sale getSaleById(final String saleId);

    CustomPage<Sale> getSales(final SalePagingRequest salePagingRequest);
    List<Sale> getSalesByCustomerId(final String customerId);

    List<SaleSearchResponse> SearchSale(SaleSearchRequest searchRequest);
}
