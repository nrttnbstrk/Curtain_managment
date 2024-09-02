package com.springboot.springbootsecurity.subProduct.model.entity;

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
@Table(name = "SUB_PRODUCT")
public class SubProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "BARCODE")
    private String barcode;

    @Column(
            name = "AMOUNT",
            precision = 24,
            scale = 4
    )
    private BigDecimal amount;

    @Column(name = "SUPPLIER")
    private String supplier;

}
