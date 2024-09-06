package com.enb.curtainmanagement.sale.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.sale.model.dto.request.SaleUpdateRequest;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SaleUpdateRequestToSaleEntityMapper extends BaseMapper<SaleUpdateRequest, SaleEntity> {


    @Named("mapForUpdating")
    default void mapForUpdating(SaleEntity saleEntityToBeUpdate, SaleUpdateRequest saleUpdateRequest) {

        saleEntityToBeUpdate.setCustomerId(saleUpdateRequest.getCustomerId());
        saleEntityToBeUpdate.setProductId(saleUpdateRequest.getProductId());
        saleEntityToBeUpdate.setSubProductId(saleUpdateRequest.getSubProductId());
        saleEntityToBeUpdate.setAmount(saleUpdateRequest.getAmount());
        saleEntityToBeUpdate.setStatus(saleUpdateRequest.getStatus());
        saleEntityToBeUpdate.setStack(saleUpdateRequest.getStack());
        saleEntityToBeUpdate.setWeight(saleUpdateRequest.getWeight());
        saleEntityToBeUpdate.setHeight(saleUpdateRequest.getHeight());
        saleEntityToBeUpdate.setWaste(saleUpdateRequest.getWaste());
        saleEntityToBeUpdate.setTotalPrice(saleUpdateRequest.getTotalPrice());
        saleEntityToBeUpdate.setInstallment(saleUpdateRequest.getInstallment());
        saleEntityToBeUpdate.setInstallmentQuantity(saleUpdateRequest.getInstallmentQuantity());
        saleEntityToBeUpdate.setInstallmentToday(saleUpdateRequest.getInstallmentToday());
        saleEntityToBeUpdate.setPaymentStatus(saleUpdateRequest.getPaymentStatus());
    }

    static SaleUpdateRequestToSaleEntityMapper initialize() {
        return Mappers.getMapper(SaleUpdateRequestToSaleEntityMapper.class);
    }

}

