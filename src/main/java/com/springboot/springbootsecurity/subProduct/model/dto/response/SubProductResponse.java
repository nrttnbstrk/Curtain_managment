package com.springboot.springbootsecurity.subProduct.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubProductResponse {
    private String id;
    private String barcode;
    private String productId;
    private BigDecimal amount;
    private String supplier;

}
