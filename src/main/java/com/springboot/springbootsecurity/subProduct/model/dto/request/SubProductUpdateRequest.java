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

    @NotNull(message = "Miktar Bos Olamaz")
    @DecimalMin(value = "0.0", inclusive = false, message = "Miktar 0'dan Kucuk Olamaz")
    private BigDecimal amount;

    @NotBlank(message = "Ana Urun Id bos olamaz.")
    private String productId;

    private String supplier;
}
