package com.springboot.springbootsecurity.customer.model.entity;

import com.springboot.springbootsecurity.common.model.entity.BaseEntity;
import com.springboot.springbootsecurity.customer.repository.CustomerRepository;
import jakarta.persistence.*;
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
@Table(name = "CUSTOMER")
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(name = "ID")
    private String id;


    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;
    @Column(name = "last_id")
    private String last_id;

    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "phone")
    private String phone;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "address")
    private String address;
    @Column(name = "detail")
    private String detail;

}
