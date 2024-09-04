package com.springboot.springbootsecurity.sale.service;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.request.SalePagingRequest;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleSearchRequest;
import com.springboot.springbootsecurity.sale.model.dto.response.SaleSearchResponse;

import java.util.List;
public interface SaleReadService {

    Sale getSaleById(final String saleId);

    CustomPage<Sale> getSales(final SalePagingRequest salePagingRequest);
    List<Sale> getSalesByCustomerId(final String customerId);

    List<SaleSearchResponse> SearchSale(SaleSearchRequest searchRequest);
}
