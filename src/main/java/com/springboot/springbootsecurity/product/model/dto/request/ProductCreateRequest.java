package com.springboot.springbootsecurity.product.model.dto.request;

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
public class ProductCreateRequest {

    @NotBlank(message = "Ürün adı boş olamaz.")
    @Size(min = 1, message = "Ürün adı boş olamaz.")
    private String name;  // adı

    @NotBlank(message = "Marka boş olamaz.")
    private String brand;  // markası

    private String code;

    private String barcode;

    @NotBlank(message = "Birim tipi boş olamaz.")
    private String unitType;  // birimtipi

    @NotNull(message = "Giriş stok adedi boş olamaz.")
    @DecimalMin(value = "0", message = "Giriş stok adedi 0 veya daha büyük olmalıdır.")
    private BigDecimal initialStockQuantity;  // giriş stok adedi


    @DecimalMin(value = "0.0001", message = "Alış fiyatı 0'dan büyük olmalıdır.")
    private BigDecimal purchasePrice;  // alış fiyatı


    @DecimalMin(value = "0.0001", message = "Satış fiyatı 0'dan büyük olmalıdır.")
    private BigDecimal sellingPrice;  // satış fiyatı


    @DecimalMin(value = "0", message = "KDV oranı 0 veya daha büyük olmalıdır.")
    private BigDecimal vatRate;  // kdv oranı

    @NotBlank(message = "Tedarikçi boş olamaz.")
    private String supplier;  // tedarikçisi
}
