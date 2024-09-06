package com.enb.curtainmanagement.installment.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentResponse {
    private String id;
    private String createdDate;
    private String customerId;
    private String installmentDate;
    private String installmentPrice;
    private String installmentWhich;
    private String saleId;
    private String status;
    private String totalPrice;
    private String installmentQuantity;



}
