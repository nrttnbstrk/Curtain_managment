package com.springboot.springbootsecurity.sale.service.impl;

import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import com.springboot.springbootsecurity.product.repository.ProductRepository;
import com.springboot.springbootsecurity.sale.exception.SaleAlreadyExistException;
import com.springboot.springbootsecurity.sale.exception.SaleNotFoundException;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleUpdateRequest;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import com.springboot.springbootsecurity.sale.model.mapper.SaleEntityToSaleMapper;
import com.springboot.springbootsecurity.sale.model.mapper.SaleUpdateRequestToSaleEntityMapper;
import com.springboot.springbootsecurity.sale.repository.SaleRepository;
import com.springboot.springbootsecurity.sale.service.SaleUpdateService;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import com.springboot.springbootsecurity.subProduct.repository.SubProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SaleUpdateServiceImpl implements SaleUpdateService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SubProductRepository subProductRepository;

    private final SaleUpdateRequestToSaleEntityMapper saleUpdateRequestToSaleEntityMapper =
            SaleUpdateRequestToSaleEntityMapper.initialize();

    private final SaleEntityToSaleMapper saleEntityToSaleMapper =
            SaleEntityToSaleMapper.initialize();

    @Override
    @Transactional
    public Sale updateSaleById(String saleId, SaleUpdateRequest saleUpdateRequest) {

        final SaleEntity saleEntityToBeUpdate = saleRepository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Verilen sale Id ile = " + saleId));

        BigDecimal previousAmount = new BigDecimal(saleEntityToBeUpdate.getAmount());

        saleUpdateRequestToSaleEntityMapper.mapForUpdating(saleEntityToBeUpdate, saleUpdateRequest);

        adjustProductAndSubProductAmounts(saleEntityToBeUpdate, previousAmount);

        SaleEntity updatedSaleEntity = saleRepository.save(saleEntityToBeUpdate);

        return saleEntityToSaleMapper.map(updatedSaleEntity);
    }

    private void adjustProductAndSubProductAmounts(SaleEntity saleEntity, BigDecimal previousAmount) {
        String productId = saleEntity.getProductId();
        String subProductId = saleEntity.getSubProductId();

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün ID bulunamadı: " + productId));

        SubProductEntity subProduct = subProductRepository.findById(subProductId)
                .orElseThrow(() -> new RuntimeException("alt ürün bulunamadı: " + subProductId));

        BigDecimal newAmount = new BigDecimal(saleEntity.getAmount());

        BigDecimal difference = newAmount.subtract(previousAmount);

        product.setTotalAmount(product.getTotalAmount().add(difference));
        subProduct.setAmount(subProduct.getAmount().subtract(difference));

        productRepository.save(product);
        subProductRepository.save(subProduct);
    }

    private void checkCustomerIdNumberUniqueness(final String saleId) {
        if (saleRepository.existsSaleEntityById(saleId)) {
            throw new SaleAlreadyExistException("Verilen ürün Id ile = " + saleId);
        }
    }
}
