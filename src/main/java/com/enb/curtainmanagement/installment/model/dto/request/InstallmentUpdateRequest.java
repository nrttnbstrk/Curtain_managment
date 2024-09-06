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
public class InstallmentUpdateRequest {

    private String customerId;
    private String saleId;
    private String status;
    private LocalDate installmentDate;
}
