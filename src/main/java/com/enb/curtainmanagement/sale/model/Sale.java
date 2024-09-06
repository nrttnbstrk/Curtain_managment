package com.enb.curtainmanagement.sale.model;

import com.enb.curtainmanagement.common.model.BaseDomainModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Sale extends BaseDomainModel {


    private String id;
    private String customerId;
    private String productId;
    private String subProductId;
    private String amount;
    private String status;
    private String stack;
    private String weight;
    private String height;
    private String waste;
    private String totalPrice;
    private String installment;
    private String installmentQuantity;
    private String installmentToday;
    private String paymentStatus;

}
