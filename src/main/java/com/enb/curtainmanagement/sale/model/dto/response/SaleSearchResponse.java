package com.enb.curtainmanagement.sale.model.dto.response;

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
    private String stack;
    private String weight;
    private String height;
    private String waste;
    private String totalPrice;
    private String installment;
    private String installmentQuantity;
    private String installmentToday;
    private String paymentStatus;
}
