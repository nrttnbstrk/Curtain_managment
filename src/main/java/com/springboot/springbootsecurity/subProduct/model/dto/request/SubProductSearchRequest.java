package com.springboot.springbootsecurity.subProduct.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SubProductSearchRequest {
    private String barcode;
    private BigDecimal amount;
    private String productId;
    private String supplier;

}
