package com.springboot.springbootsecurity.sale.service.impl;

import com.springboot.springbootsecurity.sale.exception.SaleNotFoundException;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import com.springboot.springbootsecurity.sale.repository.SaleRepository;
import com.springboot.springbootsecurity.sale.service.SaleDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleDeleteServiceImpl implements SaleDeleteService {

    private final SaleRepository saleRepository;

    @Override
    public void deleteSaleById(String saleId) {

        final SaleEntity saleEntityToBeDelete = saleRepository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("With given saleId = " + saleId));

        saleRepository.delete(saleEntityToBeDelete);

    }

}
