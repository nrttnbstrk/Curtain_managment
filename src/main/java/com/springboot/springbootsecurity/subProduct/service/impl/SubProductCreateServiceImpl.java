package com.springboot.springbootsecurity.subProduct.service.impl;

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

@Service
@RequiredArgsConstructor
public class SubProductCreateServiceImpl implements SubProductCreateService {

    private final SubProductRepository subProductRepository;

    private final SubProductCreateRequestToSubProductEntityMapper subProductCreateRequestToSubProductEntityMapper =
            SubProductCreateRequestToSubProductEntityMapper.initialize();

    private final SubProductEntityToSubProductMapper subProductEntityToSubProductMapper = SubProductEntityToSubProductMapper.initialize();

    @Override
    public SubProduct createProduct(SubProductCreateRequest subProductCreateRequest) {

        checkUniquenessProductName(subProductCreateRequest.getBarcode());

        final SubProductEntity subProductEntityToBeSave = subProductCreateRequestToSubProductEntityMapper.mapForSaving(subProductCreateRequest);

        SubProductEntity savedSubProductEntity = subProductRepository.save(subProductEntityToBeSave);

        return subProductEntityToSubProductMapper.map(savedSubProductEntity);

    }

    private void checkUniquenessProductName(final String barcode) {
        if (subProductRepository.existsProductEntityByBarcode(barcode)) {
            throw new SubProductAlreadyExistException("Verilen barkoda sahip başka bir ürün var: " + barcode);
        }
    }

}
