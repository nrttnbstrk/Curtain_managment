package com.springboot.springbootsecurity.sale.service.impl;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.sale.model.dto.response.SaleSearchResponse;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import com.springboot.springbootsecurity.sale.specification.SaleSpecification;
import com.springboot.springbootsecurity.sale.exception.SaleNotFoundException;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.request.SalePagingRequest;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleSearchRequest;
import com.springboot.springbootsecurity.sale.model.mapper.ListSaleEntityToListSaleMapper;
import com.springboot.springbootsecurity.sale.model.mapper.SaleEntityToSaleMapper;
import com.springboot.springbootsecurity.sale.repository.SaleRepository;
import com.springboot.springbootsecurity.sale.service.SaleReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SaleSearchResponse> SearchSale(SaleSearchRequest searchRequest) {
        Specification<SaleEntity> specification = Specification.where(null);

        if (searchRequest.getCustomerId() != null && !searchRequest.getCustomerId().isEmpty()) {
            specification = specification.and(SaleSpecification.hasCustomerId(searchRequest.getCustomerId()));
        }
        if (searchRequest.getProductId() != null && !searchRequest.getProductId().isEmpty()) {
            specification = specification.and(SaleSpecification.hasProductId(searchRequest.getProductId()));
        }
        if (searchRequest.getSubProductId() != null && !searchRequest.getSubProductId().isEmpty()) {
            specification = specification.and(SaleSpecification.hasSubProductId(searchRequest.getSubProductId()));
        }
        if (searchRequest.getAmount() != null && !searchRequest.getAmount().isEmpty()) {
            specification = specification.and(SaleSpecification.hasAmount(searchRequest.getAmount()));
        }
        if (searchRequest.getStatus() != null && !searchRequest.getStatus().isEmpty()) {
            specification = specification.and(SaleSpecification.hasStatus(searchRequest.getStatus()));
        }

        List<SaleEntity> saleEntities = saleRepository.findAll(specification);

        return saleEntities.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private SaleSearchResponse mapToResponse(SaleEntity entity) {
        SaleSearchResponse response = new SaleSearchResponse();
        response.setCustomerId(entity.getCustomerId());
        response.setProductId(entity.getProductId());
        response.setSubProductId(entity.getSubProductId());
        response.setStatus(entity.getStatus());
        response.setAmount(entity.getAmount());
        return response;
    }
}
