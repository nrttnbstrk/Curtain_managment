package com.enb.curtainmanagement.product.service.impl;

import com.enb.curtainmanagement.product.service.ProductDeleteService;
import com.enb.curtainmanagement.product.exception.ProductNotFoundException;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleteServiceImpl implements ProductDeleteService {

    private final ProductRepository productRepository;

    @Override
    public void deleteProductById(String productId) {

        final ProductEntity productEntityToBeDelete = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Silmek için belirtilen ürün mevcut değil."));

        productRepository.delete(productEntityToBeDelete);

    }

}
