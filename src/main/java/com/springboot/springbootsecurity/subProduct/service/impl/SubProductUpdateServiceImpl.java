package com.springboot.springbootsecurity.subProduct.service.impl;

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

@Service
@RequiredArgsConstructor
public class SubProductUpdateServiceImpl implements SubProductUpdateService {

    private final SubProductRepository subProductRepository;

    private final SubProductUpdateRequestToSubProductEntityMapper subProductUpdateRequestToSubProductEntityMapper =
            SubProductUpdateRequestToSubProductEntityMapper.initialize();

    private final SubProductEntityToSubProductMapper subProductEntityToSubProductMapper =
            SubProductEntityToSubProductMapper.initialize();

    @Override
    public SubProduct updateProductById(String productId, SubProductUpdateRequest subProductUpdateRequest) {

        final SubProductEntity subProductEntityToBeUpdate = subProductRepository
                .findById(productId)
                .orElseThrow(() -> new SubProductNotFoundException("Bu Ürün Zaten Mevcut = " + productId));

        if (!subProductEntityToBeUpdate.getBarcode().equals(subProductUpdateRequest.getBarcode())) {
            checkProductNameUniqueness(productId, subProductUpdateRequest.getBarcode());
        }

        subProductUpdateRequestToSubProductEntityMapper.mapForUpdating(subProductEntityToBeUpdate, subProductUpdateRequest);

        SubProductEntity updatedSubProductEntity = subProductRepository.save(subProductEntityToBeUpdate);

        return subProductEntityToSubProductMapper.map(updatedSubProductEntity);
    }


    private void checkProductNameUniqueness(final String productId, final String barcode) {
        boolean barcodeExists = subProductRepository.existsProductEntityByBarcodeAndIdNot(barcode, productId);
        if (barcodeExists) {
            throw new SubProductAlreadyExistException("Verilen ürün barkodu ile = " + barcode);
        }
    }


}
