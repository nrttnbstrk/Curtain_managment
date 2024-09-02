package com.springboot.springbootsecurity.subProduct.model.dto.request;

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
public class SubProductUpdateRequest {

    @NotBlank(message = "Ürün barcode boş olamaz.")
    private String barcode;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Ana Ürün Id boş olamaz.")
    private String productId;  // alış fiyatı

    private String supplier;  // tedarikçisi
}
