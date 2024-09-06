package com.enb.curtainmanagement.installment.model;

import com.enb.curtainmanagement.common.model.BaseDomainModel;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Installment extends BaseDomainModel {

    private String id;
    private LocalDate createdDate;
    private LocalDate installmentDate;
    private String customerId;
    private BigDecimal installmentPrice;
    private String installmentWhich;
    private String saleId;
    private String status;
    private BigDecimal totalPrice;
    private int installmentQuantity;

}
