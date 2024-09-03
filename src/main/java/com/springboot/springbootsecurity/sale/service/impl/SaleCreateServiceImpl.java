package com.springboot.springbootsecurity.sale.service.impl;

import com.springboot.springbootsecurity.product.repository.ProductRepository;
import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import com.springboot.springbootsecurity.sale.exception.InsufficientAmountException;
import com.springboot.springbootsecurity.subProduct.repository.SubProductRepository;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import com.springboot.springbootsecurity.common.exception.GlobalExceptionHandler;
import com.springboot.springbootsecurity.sale.exception.SaleAlreadyExistException;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleCreateRequest;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import com.springboot.springbootsecurity.sale.model.mapper.SaleCreateRequestToSaleEntityMapper;
import com.springboot.springbootsecurity.sale.model.mapper.SaleEntityToSaleMapper;
import com.springboot.springbootsecurity.sale.repository.SaleRepository;
import com.springboot.springbootsecurity.sale.service.SaleCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SaleCreateServiceImpl implements SaleCreateService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SubProductRepository subProductRepository;

    private final SaleCreateRequestToSaleEntityMapper saleCreateRequestToSaleEntityMapper =
            SaleCreateRequestToSaleEntityMapper.initialize();

    private final SaleEntityToSaleMapper saleEntityToSaleMapper = SaleEntityToSaleMapper.initialize();

    @Override
    @Transactional
    public Sale createSale(SaleCreateRequest saleCreateRequest) {

        final SaleEntity saleEntityToBeSave = saleCreateRequestToSaleEntityMapper.mapForSaving(saleCreateRequest);

        SaleEntity savedSaleEntity = saleRepository.save(saleEntityToBeSave);

        updateProductAndSubProductAmounts(savedSaleEntity);

        return saleEntityToSaleMapper.map(savedSaleEntity);
    }

    private void checkUniquenessIdNumber(final String saleId) {
        if (saleRepository.existsSaleEntityById(saleId)) {
            throw new SaleAlreadyExistException("Bu Id ile başka bir satış daha var: " + saleId);
        }
    }

    private void updateProductAndSubProductAmounts(SaleEntity saleEntity) {
        ProductEntity product = productRepository.findById(saleEntity.getProductId())
                .orElseThrow(() -> new RuntimeException("Ürün ID  bulunamadı: " + saleEntity.getProductId()));

        SubProductEntity subProduct = subProductRepository.findById(saleEntity.getSubProductId())
                .orElseThrow(() -> new RuntimeException("alt ürün bulunamadı: " + saleEntity.getSubProductId()));

        BigDecimal saleAmount = new BigDecimal(saleEntity.getAmount());

        if (subProduct.getAmount().compareTo(saleAmount) < 0) {
            throw new InsufficientAmountException("Yeterli miktar yok");
        }

        product.setTotalAmount(product.getTotalAmount().subtract(saleAmount));
        subProduct.setAmount(subProduct.getAmount().subtract(saleAmount));

        productRepository.save(product);
        subProductRepository.save(subProduct);
    }

}
