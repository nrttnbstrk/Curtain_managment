package com.springboot.springbootsecurity.sale.service.impl;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.sale.exception.SaleNotFoundException;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.request.SalePagingRequest;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import com.springboot.springbootsecurity.sale.model.mapper.ListSaleEntityToListSaleMapper;
import com.springboot.springbootsecurity.sale.model.mapper.SaleEntityToSaleMapper;
import com.springboot.springbootsecurity.sale.repository.SaleRepository;
import com.springboot.springbootsecurity.sale.service.SaleReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleReadServiceImpl implements SaleReadService {

    private final SaleRepository saleRepository;

    private final SaleEntityToSaleMapper saleEntityToSaleMapper = SaleEntityToSaleMapper.initialize();

    private final ListSaleEntityToListSaleMapper listSaleEntityToListSaleMapper =
            ListSaleEntityToListSaleMapper.initialize();

    @Override
    public Sale getSaleById(String saleId) {

        final SaleEntity saleEntityFromDB = saleRepository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Verilen sale Id ile = " + saleId));

        return saleEntityToSaleMapper.map(saleEntityFromDB);
    }
    @Override
    public List<Sale> getSalesByCustomerId(String customerId) {
        final List<SaleEntity> saleEntities = saleRepository.findByCustomerId(customerId);

        if (saleEntities.isEmpty()) {
            throw new SaleNotFoundException("customer Id için satış bulunamadı = " + customerId);
        }

        return listSaleEntityToListSaleMapper.toSaleList(saleEntities);
    }
    @Override
    public CustomPage<Sale> getSales(SalePagingRequest salePagingRequest) {

        final Page<SaleEntity> saleEntityPage = saleRepository.findAll(salePagingRequest.toPageable());

        if (saleEntityPage.getContent().isEmpty()) {
            throw new SaleNotFoundException("Herhangi bir satış bulunamadı");
        }

        final List<Sale> saleDomainModels = listSaleEntityToListSaleMapper
                .toSaleList(saleEntityPage.getContent());

        return CustomPage.of(saleDomainModels, saleEntityPage);

    }


}
