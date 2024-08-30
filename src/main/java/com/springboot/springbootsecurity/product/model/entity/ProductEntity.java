package com.springboot.springbootsecurity.product.model.entity;

import com.springboot.springbootsecurity.common.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "PRODUCT")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;  // Ürün ID'si

    @Column(name = "NAME")
    private String name;  // Ürün adı

    @Column(name = "BRAND")
    private String brand;  // Marka
    @Column(name = "CODE")
    private String code;
    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "UNIT_TYPE")
    private String unitType;  // Birim tipi

    @Column(
            name = "INITIAL_STOCK_QUANTITY",
            precision = 24,
            scale = 4
    )
    private BigDecimal initialStockQuantity;  // Giriş stok adedi

    @Column(
            name = "PURCHASE_PRICE",
            precision = 24,
            scale = 4
    )
    private BigDecimal purchasePrice;  // Alış fiyatı

    @Column(
            name = "SELLING_PRICE",
            precision = 24,
            scale = 4
    )
    private BigDecimal sellingPrice;  // Satış fiyatı

    @Column(
            name = "VAT_RATE",
            precision = 5,
            scale = 2
    )
    private BigDecimal vatRate;  // KDV oranı

    @Column(name = "SUPPLIER")
    private String supplier;  // Tedarikçi

}
