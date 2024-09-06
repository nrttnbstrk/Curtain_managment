package com.enb.curtainmanagement.installment.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InstallmentSearchRequest {
    private LocalDate createdDate;
    private String customerId;
    private LocalDate installmentDate;
    private BigDecimal installmentPrice;
    private String installmentWhich;
    private String saleId;
    private String status;
    private BigDecimal totalPrice;
    private int installmentQuantity;

}
