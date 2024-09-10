package com.enb.curtainmanagement.product.model.entity;

import com.enb.curtainmanagement.common.model.entity.BaseEntity;
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
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BRAND")
    private String brand;

    @Column(name="AUTO_BARCODE")
    private Boolean autoBarcode;

    @Column(name = "CODE")
    private String code;

    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "UNIT_TYPE")
    private String unitType;

    @Column(
            name = "TOTAL_AMOUNT",
            precision = 24,
            scale = 4
    )
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(
            name = "PURCHASE_PRICE",
            precision = 24,
            scale = 4
    )
    private BigDecimal purchasePrice;

    @Column(
            name = "SELLING_PRICE",
            precision = 24,
            scale = 4
    )
    private BigDecimal sellingPrice;


    @Column(name = "SUPPLIER")
    private String supplier;
}
