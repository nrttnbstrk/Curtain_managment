package com.springboot.springbootsecurity.product.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSearchRequest {
    private String name;
    private String brand;
    private String code;
    private String barcode;
    private String supplier;
}
