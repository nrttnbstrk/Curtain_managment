package com.springboot.springbootsecurity.sale.service.impl;

import com.springboot.springbootsecurity.sale.exception.SaleAlreadyExistException;
import com.springboot.springbootsecurity.sale.exception.SaleNotFoundException;
import com.springboot.springbootsecurity.sale.model.Sale;
import com.springboot.springbootsecurity.sale.model.dto.request.SaleUpdateRequest;
import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import com.springboot.springbootsecurity.sale.model.mapper.SaleEntityToSaleMapper;
import com.springboot.springbootsecurity.sale.model.mapper.SaleUpdateRequestToSaleEntityMapper;
import com.springboot.springbootsecurity.sale.repository.SaleRepository;
import com.springboot.springbootsecurity.sale.service.SaleUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleUpdateServiceImpl implements SaleUpdateService {

    private final SaleRepository saleRepository;

    private final SaleUpdateRequestToSaleEntityMapper saleUpdateRequestToSaleEntityMapper =
            SaleUpdateRequestToSaleEntityMapper.initialize();

    private final SaleEntityToSaleMapper saleEntityToSaleMapper =
            SaleEntityToSaleMapper.initialize();

    @Override
    public Sale updateSaleById(String saleId, SaleUpdateRequest saleUpdateRequest) {


        final SaleEntity saleEntityToBeUpdate = saleRepository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("With given saleId = " + saleId));

        saleUpdateRequestToSaleEntityMapper.mapForUpdating(saleEntityToBeUpdate, saleUpdateRequest);

        SaleEntity updatedSaleEntity = saleRepository.save(saleEntityToBeUpdate);

        return saleEntityToSaleMapper.map(updatedSaleEntity);

    }

    private void checkCustomerIdNumberUniqueness(final String saleId) {
        if (saleRepository.existsSaleEntityById(saleId)) {
            throw new SaleAlreadyExistException("With given product saleId = " + saleId);
        }
    }

}
