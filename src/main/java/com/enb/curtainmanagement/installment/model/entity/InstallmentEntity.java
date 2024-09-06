package com.enb.curtainmanagement.installment.model.entity;

import com.enb.curtainmanagement.common.model.entity.BaseEntity;
import jakarta.persistence.*;
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
@Entity
@Table(name = "INSTALLMENT")
public class InstallmentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(name = "ID")
    private String id;
    @Column(name = "CREATED_DATE" , nullable = false)
    private LocalDate createdDate;
    @Column(name = "INSTALLMENT_DATE",nullable = false)
    private LocalDate installmentDate;
    @Column(name = "CUSTOMER_ID")
    private String customerId;
    @Column(name = "INSTALLMENT_PRICE")
    private BigDecimal installmentPrice;
    @Column(name = "INSTALLMENT_WHICH")
    private String installmentWhich;
    @Column(name = "SALE_ID")
    private String saleId;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;
    @Column(name = "INSTALLMENT_QUANTITY")
    private int installmentQuantity;


}
