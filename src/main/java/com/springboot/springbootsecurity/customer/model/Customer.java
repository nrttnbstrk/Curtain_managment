package com.springboot.springbootsecurity.customer.model;

import com.springboot.springbootsecurity.common.model.BaseDomainModel;
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
public class Customer extends BaseDomainModel {


    private String id;
    private String firstname;
    private String lastname;
    private String last_id;
    private String id_number;
    private String phone;
    private String city;
    private String District;
    private String neighborhood;
    private String address;
    private String detail;
}
