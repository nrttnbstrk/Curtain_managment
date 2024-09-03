package com.springboot.springbootsecurity.sale.service.impl;

import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import com.springboot.springbootsecurity.product.repository.ProductRepository;
import com.springboot.springbootsecurity.sale.exception.SaleNotFoundException;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import com.springboot.springbootsecurity.sale.repository.SaleRepository;
import com.springboot.springbootsecurity.sale.service.SaleDeleteService;
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
                .orElseThrow(() -> new SaleNotFoundException("Verilen sale Id ile = " + saleId));

        updateProductTotalAmount(saleEntityToBeDelete.getProductId(), saleEntityToBeDelete.getAmount());

        saleRepository.delete(saleEntityToBeDelete);
    }

    private void updateProductTotalAmount(String productId, String amountToDeduct) {
        BigDecimal amountToSubtract = new BigDecimal(amountToDeduct);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün ID bulunamadı: " + productId));

        BigDecimal newTotalAmount = product.getTotalAmount().subtract(amountToSubtract);

        product.setTotalAmount(newTotalAmount);
        productRepository.save(product);
    }
}
