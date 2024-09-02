package com.springboot.springbootsecurity.product.model;

import com.springboot.springbootsecurity.common.model.BaseDomainModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseDomainModel {


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
