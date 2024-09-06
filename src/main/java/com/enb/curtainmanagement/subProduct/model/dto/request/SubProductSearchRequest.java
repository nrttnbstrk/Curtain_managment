package com.enb.curtainmanagement.subProduct.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SubProductSearchRequest {
    private String barcode;
    private BigDecimal amount;
    private String productId;
    private String supplier;

}
