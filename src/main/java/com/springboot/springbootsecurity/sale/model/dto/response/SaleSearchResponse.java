package com.springboot.springbootsecurity.sale.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleSearchResponse {
    private String customerId;
    private String productId;
    private String subProductId;
    private String amount;
    private String status;
}
