package com.springboot.springbootsecurity.customer.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUpdateRequest {

    @NotBlank(message = "Müşteri adı boş olamaz.")
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
