package com.enb.curtainmanagement.sale.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.sale.model.dto.request.SaleCreateRequest;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleCreateRequestToSaleEntityMapper extends BaseMapper<SaleCreateRequest, SaleEntity> {

    @Named("mapForSaving")
    default SaleEntity mapForSaving(SaleCreateRequest saleCreateRequest) {
        return SaleEntity.builder()
                .customerId(saleCreateRequest.getCustomerId())
                .productId(saleCreateRequest.getProductId())
                .subProductId(saleCreateRequest.getSubProductId())
                .amount(saleCreateRequest.getAmount())
                .status(saleCreateRequest.getStatus())
                .stack(saleCreateRequest.getStack())
                .weight(saleCreateRequest.getWeight())
                .height(saleCreateRequest.getHeight())
                .waste(saleCreateRequest.getWaste())
                .totalPrice(saleCreateRequest.getTotalPrice())
                .installment(saleCreateRequest.getInstallment())
                .installmentQuantity(saleCreateRequest.getInstallmentQuantity())
                .installmentToday(saleCreateRequest.getInstallmentToday())
                .paymentStatus(saleCreateRequest.getPaymentStatus())
                .build();
    }
    static SaleCreateRequestToSaleEntityMapper initialize() {
        return Mappers.getMapper(SaleCreateRequestToSaleEntityMapper.class);
    }

}
