package com.springboot.springbootsecurity.sale.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleUpdateRequest {

    private String customerId;
    private String productId;
    private String subProductId;
    private String amount;
    private String status;
}
