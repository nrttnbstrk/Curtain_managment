package com.enb.curtainmanagement.subProduct.model;

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
public class SubProduct extends BaseDomainModel {


    private String id;
    private String productId;
    private String barcode;
    private BigDecimal amount;
    private String supplier;
}
