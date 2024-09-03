package com.springboot.springbootsecurity.product.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateRequest {

    @NotBlank(message = "Ürün adı boş olamaz.")
    @Size(min = 1, message = "Ürün adı boş olamaz.")
    private String name;

    @NotBlank(message = "Marka boş olamaz.")
    private String brand;

    private String code;

    private String barcode;

    @NotBlank(message = "Birim tipi boş olamaz.")
    private String unitType;


    @DecimalMin(value = "0.0001", message = "Alış fiyatı 0'dan büyük olmalıdır.")
    private BigDecimal purchasePrice;


    @DecimalMin(value = "0.0001", message = "Satış fiyatı 0'dan büyük olmalıdır.")
    private BigDecimal sellingPrice;

    @NotBlank(message = "Tedarikçi boş olamaz.")
    private String supplier;
}
