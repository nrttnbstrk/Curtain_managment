package com.enb.curtainmanagement.product.service.impl;

import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.service.ProductUpdateService;
import com.enb.curtainmanagement.product.exception.ProductAlreadyExistException;
import com.enb.curtainmanagement.product.exception.ProductNotFoundException;
import com.enb.curtainmanagement.product.model.dto.request.ProductUpdateRequest;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.model.mapper.ProductEntityToProductMapper;
import com.enb.curtainmanagement.product.model.mapper.ProductUpdateRequestToProductEntityMapper;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdateService {

    private final ProductRepository productRepository;

    private final ProductUpdateRequestToProductEntityMapper productUpdateRequestToProductEntityMapper =
            ProductUpdateRequestToProductEntityMapper.initialize();

    private final ProductEntityToProductMapper productEntityToProductMapper =
            ProductEntityToProductMapper.initialize();

    @Override
    public Product updateProductById(String productId, ProductUpdateRequest productUpdateRequest) {

        checkProductNameUniqueness(productUpdateRequest.getName());

        final ProductEntity productEntityToBeUpdate = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Böyle bir ürün mevcut değil"));

        productUpdateRequestToProductEntityMapper.mapForUpdating(productEntityToBeUpdate, productUpdateRequest);

        ProductEntity updatedProductEntity = productRepository.save(productEntityToBeUpdate);

        return productEntityToProductMapper.map(updatedProductEntity);

    }

    private void checkProductNameUniqueness(final String productName) {
        if (productRepository.existsProductEntityByName(productName)) {
            throw new ProductAlreadyExistException("Bu isim ile başka bir ürün kayıtlı");
        }
    }

}
