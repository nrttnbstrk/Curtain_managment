package com.enb.curtainmanagement.sale.service.impl;

import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import com.enb.curtainmanagement.sale.exception.SaleNotFoundException;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import com.enb.curtainmanagement.sale.repository.SaleRepository;
import com.enb.curtainmanagement.sale.service.SaleDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SaleDeleteServiceImpl implements SaleDeleteService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void deleteSaleById(String saleId) {

        final SaleEntity saleEntityToBeDelete = saleRepository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Belirtilen SATIS mevcut değil."));

        updateProductTotalAmount(saleEntityToBeDelete.getProductId(), saleEntityToBeDelete.getAmount());

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
}
