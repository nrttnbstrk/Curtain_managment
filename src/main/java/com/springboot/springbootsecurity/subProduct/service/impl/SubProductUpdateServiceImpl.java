package com.springboot.springbootsecurity.subProduct.service.impl;

import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import com.springboot.springbootsecurity.product.repository.ProductRepository;
import com.springboot.springbootsecurity.subProduct.exception.SubProductAlreadyExistException;
import com.springboot.springbootsecurity.subProduct.exception.SubProductNotFoundException;
import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductUpdateRequest;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import com.springboot.springbootsecurity.subProduct.model.mapper.SubProductEntityToSubProductMapper;
import com.springboot.springbootsecurity.subProduct.model.mapper.SubProductUpdateRequestToSubProductEntityMapper;
import com.springboot.springbootsecurity.subProduct.repository.SubProductRepository;
import com.springboot.springbootsecurity.subProduct.service.SubProductUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubProductUpdateServiceImpl implements SubProductUpdateService {

    private final SubProductRepository subProductRepository;
    private final ProductRepository productRepository;

    private final SubProductUpdateRequestToSubProductEntityMapper subProductUpdateRequestToSubProductEntityMapper =
            SubProductUpdateRequestToSubProductEntityMapper.initialize();

    private final SubProductEntityToSubProductMapper subProductEntityToSubProductMapper =
            SubProductEntityToSubProductMapper.initialize();

    @Override
    @Transactional
    public SubProduct updateProductById(String subProductId, SubProductUpdateRequest subProductUpdateRequest) {

        final SubProductEntity subProductEntityToBeUpdate = subProductRepository
                .findById(subProductId)
                .orElseThrow(() -> new SubProductNotFoundException("Belirtilen ALT URUN mevcut değil."));

        if (!subProductEntityToBeUpdate.getBarcode().equals(subProductUpdateRequest.getBarcode())) {
            checkProductNameUniqueness(subProductId, subProductUpdateRequest.getBarcode());
        }

        BigDecimal previousAmount = subProductEntityToBeUpdate.getAmount();

        subProductUpdateRequestToSubProductEntityMapper.mapForUpdating(subProductEntityToBeUpdate, subProductUpdateRequest);

        SubProductEntity updatedSubProductEntity = subProductRepository.save(subProductEntityToBeUpdate);

        adjustProductTotalAmount(updatedSubProductEntity.getProductId(), previousAmount, updatedSubProductEntity.getAmount());

        return subProductEntityToSubProductMapper.map(updatedSubProductEntity);
    }

    private void adjustProductTotalAmount(String productId, BigDecimal previousAmount, BigDecimal newAmount) {
        BigDecimal difference = newAmount.subtract(previousAmount);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Belirtilen ALT URUN mevcut değil."));

        product.setTotalAmount(product.getTotalAmount().add(difference));
        productRepository.save(product);
    }

    private void checkProductNameUniqueness(final String subProductId, final String barcode) {
        boolean barcodeExists = subProductRepository.existsProductEntityByBarcodeAndIdNot(barcode, subProductId);
        if (barcodeExists) {
            throw new SubProductAlreadyExistException("Girilen ALT URUN barkodu zaten mevcut.");
        }
    }
}
