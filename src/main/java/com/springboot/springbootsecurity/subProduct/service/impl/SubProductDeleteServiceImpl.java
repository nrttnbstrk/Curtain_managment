package com.springboot.springbootsecurity.subProduct.service.impl;

import com.springboot.springbootsecurity.subProduct.exception.SubProductNotFoundException;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import com.springboot.springbootsecurity.subProduct.repository.SubProductRepository;
import com.springboot.springbootsecurity.subProduct.service.SubProductDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubProductDeleteServiceImpl implements SubProductDeleteService {

    private final SubProductRepository subProductRepository;

    @Override
    public void deleteProductById(String productId) {

        final SubProductEntity subProductEntityToBeDelete = subProductRepository
                .findById(productId)
                .orElseThrow(() -> new SubProductNotFoundException("Verilen productID = " + productId));

        subProductRepository.delete(subProductEntityToBeDelete);

    }

}
