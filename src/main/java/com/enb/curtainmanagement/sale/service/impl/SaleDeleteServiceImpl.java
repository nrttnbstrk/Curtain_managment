package com.enb.curtainmanagement.sale.service.impl;

import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import com.enb.curtainmanagement.sale.exception.SaleNotFoundException;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import com.enb.curtainmanagement.sale.repository.SaleRepository;
import com.enb.curtainmanagement.sale.service.SaleDeleteService;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
import com.enb.curtainmanagement.subProduct.repository.SubProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SaleDeleteServiceImpl implements SaleDeleteService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SubProductRepository subProductRepository;

    @Override
    @Transactional
    public void deleteSaleById(String saleId) {

        final SaleEntity saleEntityToBeDelete = saleRepository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Belirtilen SATIS mevcut değil."));

        if ("cut".equals(saleEntityToBeDelete.getStatus())) {
            updateProductTotalAmount(saleEntityToBeDelete.getProductId(), saleEntityToBeDelete.getAmount());
        } else {
            updateSubProductWaitAmount(saleEntityToBeDelete.getSubProductId(), saleEntityToBeDelete.getAmount());
        }

        saleRepository.delete(saleEntityToBeDelete);
    }

    private void updateProductTotalAmount(String productId, String amountToDeduct) {
        BigDecimal amountToSubtract = new BigDecimal(amountToDeduct);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Belirtilen URUN mevcut değil."));

        BigDecimal newTotalAmount = product.getTotalAmount().subtract(amountToSubtract);

        product.setTotalAmount(newTotalAmount);
        productRepository.save(product);
    }

    private void updateSubProductWaitAmount(String subProductId, String amountToDeduct) {
        BigDecimal amountToSubtract = new BigDecimal(amountToDeduct);

        SubProductEntity subProduct = subProductRepository.findById(subProductId)
                .orElseThrow(() -> new RuntimeException("Belirtilen ALT URUN mevcut değil."));

        BigDecimal newWaitAmount = subProduct.getWaitAmount().subtract(amountToSubtract);

        subProduct.setWaitAmount(newWaitAmount);
        subProductRepository.save(subProduct);
    }
}
