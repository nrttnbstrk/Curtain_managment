package com.enb.curtainmanagement.sale.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponse {
    private String id;
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
