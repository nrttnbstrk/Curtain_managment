package com.springboot.springbootsecurity.sale.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleSearchRequest {
    private String customerId;
    private String productId;
    private String subProductId;
    private String amount;
    private String status;
}
