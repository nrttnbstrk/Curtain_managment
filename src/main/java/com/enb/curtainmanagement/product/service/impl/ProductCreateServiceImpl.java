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

        checkUniquenessProductName(productCreateRequest.getName());
        checkUniquenessProductCode(productCreateRequest.getCode());

        // Otomatik barkod oluşturma işlemi
        if (productCreateRequest.getAutoBarcode() && productCreateRequest.getBarcode().isEmpty()) {
            String generatedBarcode = generateUniqueBarcode();
            productCreateRequest.setBarcode(generatedBarcode);
        } else {
            checkUniquenessProductBarcode(productCreateRequest.getBarcode());
        }

        final ProductEntity productEntityToBeSave = productCreateRequestToProductEntityMapper.mapForSaving(productCreateRequest);

        if (productEntityToBeSave.getTotalAmount() == null) {
            productEntityToBeSave.setTotalAmount(BigDecimal.ZERO);
        }

        ProductEntity savedProductEntity = productRepository.save(productEntityToBeSave);

        return productEntityToProductMapper.map(savedProductEntity);
    }

    private String generateUniqueBarcode() {
        String barcode;
        do {
            barcode = String.valueOf(System.currentTimeMillis()); // Basit bir benzersiz barkod üretme mantığı
        } while (productRepository.existsProductEntityByBarcode(barcode)); // Benzersizliği kontrol et
        return barcode;
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


