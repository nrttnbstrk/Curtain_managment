package com.enb.curtainmanagement.sale.service.impl;

import com.enb.curtainmanagement.customer.repository.CustomerRepository;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
import com.enb.curtainmanagement.subProduct.repository.SubProductRepository;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.repository.ProductRepository;
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
    //private final CustomerRepository customerRepository;
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

        String previousStatus = saleEntityToBeUpdate.getStatus();
        BigDecimal previousAmount = new BigDecimal(saleEntityToBeUpdate.getAmount());

        String productId = saleEntityToBeUpdate.getProductId();
        String subProductId = saleEntityToBeUpdate.getSubProductId();

        saleUpdateRequestToSaleEntityMapper.mapForUpdating(saleEntityToBeUpdate, saleUpdateRequest);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Belirtilen ÜRÜN mevcut değil."));

        SubProductEntity subProduct = subProductRepository.findById(subProductId)
                .orElseThrow(() -> new RuntimeException("Belirtilen ALT ÜRÜN mevcut değil."));

        if ("wait".equals(previousStatus) && "wait".equals(saleEntityToBeUpdate.getStatus())) {
            adjustWaitAmountForWaitStatusChange(saleEntityToBeUpdate, previousAmount, subProduct);
        } else if ("cut".equals(previousStatus) && "wait".equals(saleEntityToBeUpdate.getStatus())) {
            adjustAmountsForCutToWait(saleEntityToBeUpdate, previousAmount, product, subProduct);
        } else if (!"cut".equals(previousStatus) && "cut".equals(saleEntityToBeUpdate.getStatus())) {
            adjustWaitAndTotalAmountForNonCutToCut(saleEntityToBeUpdate, product, subProduct);
        } else if ("cut".equals(previousStatus) && "cut".equals(saleEntityToBeUpdate.getStatus())) {
            adjustProductAndSubProductAmountsForCutStatusChange(saleEntityToBeUpdate, previousAmount, product, subProduct);
        }

        SaleEntity updatedSaleEntity = saleRepository.save(saleEntityToBeUpdate);

        return saleEntityToSaleMapper.map(updatedSaleEntity);
    }

    private void adjustWaitAmountForWaitStatusChange(SaleEntity saleEntity, BigDecimal previousAmount, SubProductEntity subProduct) {
        BigDecimal newAmount = new BigDecimal(saleEntity.getAmount());
        BigDecimal difference = newAmount.subtract(previousAmount);

        if (difference.compareTo(BigDecimal.ZERO) > 0) {
            subProduct.setWaitAmount(subProduct.getWaitAmount().add(difference));
        } else if (difference.compareTo(BigDecimal.ZERO) < 0) {
            subProduct.setWaitAmount(subProduct.getWaitAmount().subtract(difference.abs()));
        }

        subProductRepository.save(subProduct);
    }

    private void adjustAmountsForCutToWait(SaleEntity saleEntity, BigDecimal previousAmount, ProductEntity product, SubProductEntity subProduct) {
        subProduct.setWaitAmount(subProduct.getWaitAmount().add(previousAmount));
        product.setTotalAmount(product.getTotalAmount().add(previousAmount));

        productRepository.save(product);
        subProductRepository.save(subProduct);
    }

    private void adjustWaitAndTotalAmountForNonCutToCut(SaleEntity saleEntity, ProductEntity product, SubProductEntity subProduct) {
        BigDecimal newAmount = new BigDecimal(saleEntity.getAmount());

        subProduct.setWaitAmount(subProduct.getWaitAmount().subtract(newAmount));
        product.setTotalAmount(product.getTotalAmount().subtract(newAmount));

        subProductRepository.save(subProduct);
        productRepository.save(product);
    }

    private void adjustProductAndSubProductAmountsForCutStatusChange(SaleEntity saleEntity, BigDecimal previousAmount, ProductEntity product, SubProductEntity subProduct) {
        BigDecimal newAmount = new BigDecimal(saleEntity.getAmount());
        BigDecimal difference = newAmount.subtract(previousAmount);

        if (difference.compareTo(BigDecimal.ZERO) > 0) {
            product.setTotalAmount(product.getTotalAmount().subtract(difference));
            subProduct.setAmount(subProduct.getAmount().subtract(difference));
        } else if (difference.compareTo(BigDecimal.ZERO) < 0) {
            product.setTotalAmount(product.getTotalAmount().add(difference.abs()));
            subProduct.setAmount(subProduct.getAmount().add(difference.abs()));
        }

        productRepository.save(product);
        subProductRepository.save(subProduct);
    }

}
