package com.enb.curtainmanagement.subProduct.service.impl;

import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import com.enb.curtainmanagement.sale.repository.SaleRepository;
import com.enb.curtainmanagement.subProduct.exception.SubProductAlreadyExistException;
import com.enb.curtainmanagement.subProduct.exception.SubProductNotFoundException;
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

    private final SubProductCreateRequestToSubProductEntityMapper subProductCreateRequestToSubProductEntityMapper =
            SubProductCreateRequestToSubProductEntityMapper.initialize();

    private final SubProductEntityToSubProductMapper subProductEntityToSubProductMapper = SubProductEntityToSubProductMapper.initialize();

    @Override
    @Transactional
    public SubProduct createProduct(SubProductCreateRequest subProductCreateRequest) {

        // Otomatik barkod oluşturma işlemi
        if (subProductCreateRequest.getAutoBarcode() && subProductCreateRequest.getBarcode().isEmpty()) {
            String generatedBarcode = generateUniqueBarcode();
            subProductCreateRequest.setBarcode(generatedBarcode);
        } else {
            checkUniquenessProductName(subProductCreateRequest.getBarcode());
        }

        final SubProductEntity subProductEntityToBeSave = subProductCreateRequestToSubProductEntityMapper.mapForSaving(subProductCreateRequest);

        if (subProductEntityToBeSave.getWaitAmount() == null) {
            subProductEntityToBeSave.setWaitAmount(BigDecimal.ZERO);
        }

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
                .orElseThrow(() -> new SubProductNotFoundException("Belirtilen URUN mevcut değil."));

        product.setTotalAmount(product.getTotalAmount().add(amountToAdd));
        productRepository.save(product);
    }

    private String generateUniqueBarcode() {
        String barcode;
        do {
            barcode = String.valueOf(System.currentTimeMillis()); // Basit bir benzersiz barkod üretme mantığı
        } while (subProductRepository.existsProductEntityByBarcode(barcode)); // Benzersizliği kontrol et
        return barcode;
    }
}
