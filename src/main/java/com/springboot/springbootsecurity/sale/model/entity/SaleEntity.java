package com.springboot.springbootsecurity.sale.model.entity;

import com.springboot.springbootsecurity.common.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

}
