package com.springboot.springbootsecurity.sale.service.impl;

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

@Service
@RequiredArgsConstructor
public class SaleCreateServiceImpl implements SaleCreateService {

    private final SaleRepository saleRepository;

    private final SaleCreateRequestToSaleEntityMapper saleCreateRequestToSaleEntityMapper =
            SaleCreateRequestToSaleEntityMapper.initialize();

    private final SaleEntityToSaleMapper saleEntityToSaleMapper = SaleEntityToSaleMapper.initialize();

    @Override
    public Sale createSale(SaleCreateRequest saleCreateRequest) {

        final SaleEntity saleEntityToBeSave = saleCreateRequestToSaleEntityMapper.mapForSaving(saleCreateRequest);

        SaleEntity savedSaleEntity = saleRepository.save(saleEntityToBeSave);

        return saleEntityToSaleMapper.map(savedSaleEntity);

    }

    private void checkUniquenessIdNumber(final String saleId) {
        if (saleRepository.existsSaleEntityById(saleId)) {
            throw new SaleAlreadyExistException("There is another customer with id number: " + saleId);
        }
    }

}
