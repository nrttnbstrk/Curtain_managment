package com.enb.curtainmanagement.product.model;

import com.enb.curtainmanagement.common.model.BaseDomainModel;
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
