package com.enb.curtainmanagement.product.service.impl;

import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.service.ProductCreateService;
import com.enb.curtainmanagement.product.exception.ProductAlreadyExistException;
import com.enb.curtainmanagement.product.model.dto.request.ProductCreateRequest;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.model.mapper.ProductCreateRequestToProductEntityMapper;
import com.enb.curtainmanagement.product.model.mapper.ProductEntityToProductMapper;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductCreateServiceImpl implements ProductCreateService {

    private final ProductRepository productRepository;

    private final ProductCreateRequestToProductEntityMapper productCreateRequestToProductEntityMapper =
            ProductCreateRequestToProductEntityMapper.initialize();

    private final ProductEntityToProductMapper productEntityToProductMapper = ProductEntityToProductMapper.initialize();

    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {

        // Benzersiz ürün adı, barkod ve kod kontrolleri
        checkUniquenessProductName(productCreateRequest.getName());
        checkUniquenessProductBarcode(productCreateRequest.getBarcode());
        checkUniquenessProductCode(productCreateRequest.getCode());

        // Request'i Entity'e dönüştürme
        final ProductEntity productEntityToBeSave = productCreateRequestToProductEntityMapper.mapForSaving(productCreateRequest);

        // TotalAmount alanı için varsayılan 0 değeri atanması
        if (productEntityToBeSave.getTotalAmount() == null) {
            productEntityToBeSave.setTotalAmount(BigDecimal.ZERO);
        }

        // Ürünü kaydetme
        ProductEntity savedProductEntity = productRepository.save(productEntityToBeSave);

        // Entity'i model'e dönüştürme ve geri döndürme
        return productEntityToProductMapper.map(savedProductEntity);
    }


    private void checkUniquenessProductName(final String productName) {
        if (productRepository.existsProductEntityByName(productName)) {
            throw new ProductAlreadyExistException("Bu isim ile başka bir ürün kayıtlı");
        }
    }
    private void checkUniquenessProductBarcode(final String barcode) {
        if (productRepository.existsProductEntityByBarcode(barcode)) {
            throw new ProductAlreadyExistException("Bu barkod ile başka bir ürün kayıtlı");
        }
    }
    private void checkUniquenessProductCode(final String code) {
        if (productRepository.existsProductEntityByCode(code)) {
            throw new ProductAlreadyExistException("Bu Urun Kodu ile başka bir ürün kayıtlı");
        }
    }
}
