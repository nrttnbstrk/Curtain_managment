package com.enb.curtainmanagement.subProduct.service.impl;

import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import com.enb.curtainmanagement.sale.repository.SaleRepository;
import com.enb.curtainmanagement.subProduct.exception.SubProductAlreadyExistException;
import com.enb.curtainmanagement.subProduct.model.SubProduct;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductCreateRequest;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
import com.enb.curtainmanagement.subProduct.model.mapper.SubProductCreateRequestToSubProductEntityMapper;
import com.enb.curtainmanagement.subProduct.model.mapper.SubProductEntityToSubProductMapper;
import com.enb.curtainmanagement.subProduct.repository.SubProductRepository;
import com.enb.curtainmanagement.subProduct.service.SubProductCreateService;
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
