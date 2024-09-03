package com.springboot.springbootsecurity.product.model.dto.response;

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
public class ProductResponse {
    private String id;
    private String name;
    private String brand;
    private String code;
    private String barcode;
    private String unitType;
    private BigDecimal totalAmount;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private String supplier;








}
