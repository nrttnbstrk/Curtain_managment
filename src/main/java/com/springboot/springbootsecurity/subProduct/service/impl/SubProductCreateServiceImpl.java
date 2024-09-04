package com.springboot.springbootsecurity.subProduct.service.impl;

import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import com.springboot.springbootsecurity.product.repository.ProductRepository;
import com.springboot.springbootsecurity.sale.repository.SaleRepository;
import com.springboot.springbootsecurity.subProduct.exception.SubProductAlreadyExistException;
import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductCreateRequest;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import com.springboot.springbootsecurity.subProduct.model.mapper.SubProductCreateRequestToSubProductEntityMapper;
import com.springboot.springbootsecurity.subProduct.model.mapper.SubProductEntityToSubProductMapper;
import com.springboot.springbootsecurity.subProduct.repository.SubProductRepository;
import com.springboot.springbootsecurity.subProduct.service.SubProductCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SubProductCreateServiceImpl implements SubProductCreateService {

    private final SubProductRepository subProductRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    private final SubProductCreateRequestToSubProductEntityMapper subProductCreateRequestToSubProductEntityMapper =
            SubProductCreateRequestToSubProductEntityMapper.initialize();

    private final SubProductEntityToSubProductMapper subProductEntityToSubProductMapper = SubProductEntityToSubProductMapper.initialize();

    @Override
    @Transactional
    public SubProduct createProduct(SubProductCreateRequest subProductCreateRequest) {

        checkUniquenessProductName(subProductCreateRequest.getBarcode());

        final SubProductEntity subProductEntityToBeSave = subProductCreateRequestToSubProductEntityMapper.mapForSaving(subProductCreateRequest);

        SubProductEntity savedSubProductEntity = subProductRepository.save(subProductEntityToBeSave);

        updateProductTotalAmount(savedSubProductEntity.getProductId(), savedSubProductEntity.getAmount());

        return subProductEntityToSubProductMapper.map(savedSubProductEntity);
    }

    private void checkUniquenessProductName(final String barcode) {
        if (subProductRepository.existsProductEntityByBarcode(barcode)) {
            throw new SubProductAlreadyExistException("Verilen barkoda sahip başka bir ALT URUN zaten mevcut.");
        }
    }

    private void updateProductTotalAmount(String productId, BigDecimal amountToAdd) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Belirtilen ALT URUN mevcut değil."));

        product.setTotalAmount(product.getTotalAmount().add(amountToAdd));
        productRepository.save(product);
    }
}
