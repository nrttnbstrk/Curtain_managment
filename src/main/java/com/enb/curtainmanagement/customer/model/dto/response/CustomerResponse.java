package com.enb.curtainmanagement.customer.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String last_id;
    private String idNumber;
    private String phone;
    private String city;
    private String district;
    private String neighborhood;
    private String address;
    private String detail;


}
