package com.enb.curtainmanagement.sale.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleCreateRequest {



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
