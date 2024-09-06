package com.enb.curtainmanagement.installment.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstallmentCreateRequest {

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
