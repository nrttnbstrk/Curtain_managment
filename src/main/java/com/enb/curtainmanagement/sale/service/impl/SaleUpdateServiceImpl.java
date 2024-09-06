package com.enb.curtainmanagement.sale.service.impl;

import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
import com.enb.curtainmanagement.customer.repository.CustomerRepository;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
import com.enb.curtainmanagement.subProduct.repository.SubProductRepository;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import com.enb.curtainmanagement.sale.exception.SaleAlreadyExistException;
import com.enb.curtainmanagement.sale.exception.SaleNotFoundException;
import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.dto.request.SaleUpdateRequest;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import com.enb.curtainmanagement.sale.model.mapper.SaleEntityToSaleMapper;
import com.enb.curtainmanagement.sale.model.mapper.SaleUpdateRequestToSaleEntityMapper;
import com.enb.curtainmanagement.sale.repository.SaleRepository;
import com.enb.curtainmanagement.sale.service.SaleUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SaleUpdateServiceImpl implements SaleUpdateService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SubProductRepository subProductRepository;
    private final CustomerRepository customerRepository;
    private final SaleUpdateRequestToSaleEntityMapper saleUpdateRequestToSaleEntityMapper =
            SaleUpdateRequestToSaleEntityMapper.initialize();

    private final SaleEntityToSaleMapper saleEntityToSaleMapper =
            SaleEntityToSaleMapper.initialize();

    @Override
    @Transactional
    public Sale updateSaleById(String saleId, SaleUpdateRequest saleUpdateRequest) {

        final SaleEntity saleEntityToBeUpdate = saleRepository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Belirtilen SATIS mevcut değil."));

        BigDecimal previousAmount = new BigDecimal(saleEntityToBeUpdate.getAmount());

        saleUpdateRequestToSaleEntityMapper.mapForUpdating(saleEntityToBeUpdate, saleUpdateRequest);

        adjustProductAndSubProductAmounts(saleEntityToBeUpdate, previousAmount);

        SaleEntity updatedSaleEntity = saleRepository.save(saleEntityToBeUpdate);

        return saleEntityToSaleMapper.map(updatedSaleEntity);
    }

    private void adjustProductAndSubProductAmounts(SaleEntity saleEntity, BigDecimal previousAmount) {
        String productId = saleEntity.getProductId();
        String subProductId = saleEntity.getSubProductId();

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Belirtilen URUN mevcut değil."));

        SubProductEntity subProduct = subProductRepository.findById(subProductId)
                .orElseThrow(() -> new RuntimeException("Belirtilen ALT URUN mevcut değil. "));
        CustomerEntity customer = customerRepository.findById(saleEntity.getCustomerId())
                .orElseThrow(() -> new SaleNotFoundException("Belirtilen MÜŞTERİ mevcut değil."));

        BigDecimal newAmount = new BigDecimal(saleEntity.getAmount());

        BigDecimal difference = newAmount.subtract(previousAmount);

        product.setTotalAmount(product.getTotalAmount().add(difference));
        subProduct.setAmount(subProduct.getAmount().subtract(difference));

        productRepository.save(product);
        subProductRepository.save(subProduct);
    }

    private void checkCustomerIdNumberUniqueness(final String saleId) {
        if (saleRepository.existsSaleEntityById(saleId)) {
            throw new SaleAlreadyExistException("Verilen ürün Id ile = " + saleId);
        }
    }
}
