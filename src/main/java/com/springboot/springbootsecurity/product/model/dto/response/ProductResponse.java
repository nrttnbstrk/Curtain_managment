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
    private String id;  // Ürün ID'si
    private String name;  // Ürün adı
    private String brand;  // Marka
    private String code;
    private String barcode;
    private String unitType;  // Birim tipi
    private BigDecimal totalAmount;  // Giriş stok adedi
    private BigDecimal purchasePrice;  // Alış fiyatı
    private BigDecimal sellingPrice;  // Satış fiyatı
    private String supplier;  // Tedarikçi








}
