package com.springboot.springbootsecurity.subProduct.service.impl;

import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import com.springboot.springbootsecurity.product.repository.ProductRepository;
import com.springboot.springbootsecurity.subProduct.exception.SubProductNotFoundException;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import com.springboot.springbootsecurity.subProduct.repository.SubProductRepository;
import com.springboot.springbootsecurity.subProduct.service.SubProductDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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
                .orElseThrow(() -> new SubProductNotFoundException("Verilen productID = " + subProductId));

        BigDecimal amountToSubtract = subProductEntityToBeDelete.getAmount();

        subProductRepository.delete(subProductEntityToBeDelete);

        adjustProductTotalAmount(subProductEntityToBeDelete.getProductId(), amountToSubtract);
    }

    private void adjustProductTotalAmount(String productId, BigDecimal amountToSubtract) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün ID bulunamadı: " + productId));

        product.setTotalAmount(product.getTotalAmount().subtract(amountToSubtract));
        productRepository.save(product);
    }
}
