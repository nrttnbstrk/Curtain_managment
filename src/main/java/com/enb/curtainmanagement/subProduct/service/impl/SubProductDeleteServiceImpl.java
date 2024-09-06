package com.enb.curtainmanagement.subProduct.service.impl;

import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import com.enb.curtainmanagement.subProduct.exception.SubProductNotFoundException;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
import com.enb.curtainmanagement.subProduct.repository.SubProductRepository;
import com.enb.curtainmanagement.subProduct.service.SubProductDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SubProductDeleteServiceImpl implements SubProductDeleteService {

    private final SubProductRepository subProductRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void deleteProductById(String subProductId) {

        final SubProductEntity subProductEntityToBeDelete = subProductRepository
                .findById(subProductId)
                .orElseThrow(() -> new SubProductNotFoundException("Silmek istediğiniz ALT URUN mevcut değil."));

        BigDecimal amountToSubtract = subProductEntityToBeDelete.getAmount();

        subProductRepository.delete(subProductEntityToBeDelete);

        adjustProductTotalAmount(subProductEntityToBeDelete.getProductId(), amountToSubtract);
    }

    private void adjustProductTotalAmount(String productId, BigDecimal amountToSubtract) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Belirtilen ALT URUN mevcut değil."));

        product.setTotalAmount(product.getTotalAmount().subtract(amountToSubtract));
        productRepository.save(product);
    }
}
