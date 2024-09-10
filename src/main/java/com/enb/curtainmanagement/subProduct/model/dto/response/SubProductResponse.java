package com.enb.curtainmanagement.subProduct.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubProductResponse {
    private String id;
    private String barcode;
    private Boolean autoBarcode;
    private String productId;
    private BigDecimal amount;
    private String supplier;

}
