package com.enb.curtainmanagement.sale.model.entity;

import com.enb.curtainmanagement.common.model.entity.BaseEntity;
import jakarta.persistence.*;
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
@Entity
@Table(name = "SALE")
public class SaleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(name = "ID")
    private String id;
    @Column(name = "CUSTOMER_ID")
    private String customerId;
    @Column(name = "PRODUCT_ID")
    private String productId;
    @Column(name = "SUB_PRODUCT_ID")
    private String subProductId;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "STACK")
    private String stack;
    @Column(name = "WEIGHT")
    private String weight;
    @Column(name = "HEIGHT")
    private String height;
    @Column(name = "WASTE")
    private String waste;
    @Column(name = "TOTAL_PRICE")
    private String totalPrice;
    @Column(name = "INSTALLMENT")
    private String installment;
    @Column(name = "INSTALLMENT_QUANTITY")
    private String installmentQuantity;
    @Column(name = "INSTALLMENT_TODAY")
    private String installmentToday;
    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;
}
