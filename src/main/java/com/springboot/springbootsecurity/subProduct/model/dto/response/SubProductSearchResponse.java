package com.springboot.springbootsecurity.subProduct.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SubProductSearchResponse {
    private String id;
    private String productId;
    private String barcode;
    private String supplier;
}