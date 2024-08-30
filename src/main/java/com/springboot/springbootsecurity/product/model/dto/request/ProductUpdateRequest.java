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
public class ProductUpdateRequest {

    @NotBlank(message = "Ürün adı boş olamaz.")
    @Size(min = 1, message = "Ürün adı boş olamaz.")
    private String name;  // Ürün adı

    @NotBlank(message = "Marka boş olamaz.")
    private String brand;  // Marka
    private String code;

    private String barcode;
    @NotBlank(message = "Birim tipi boş olamaz.")
    private String unitType;  // Birim tipi

    @NotNull(message = "Giriş stok adedi boş olamaz.")
    @DecimalMin(value = "0.0001", message = "Giriş stok adedi 0'dan büyük olmalıdır.")
    private BigDecimal initialStockQuantity;  // Giriş stok adedi

    @NotNull(message = "Alış fiyatı boş olamaz.")
    @DecimalMin(value = "0.0001", message = "Alış fiyatı 0'dan büyük olmalıdır.")
    private BigDecimal purchasePrice;  // Alış fiyatı

    @NotNull(message = "Satış fiyatı boş olamaz.")
    @DecimalMin(value = "0.0001", message = "Satış fiyatı 0'dan büyük olmalıdır.")
    private BigDecimal sellingPrice;  // Satış fiyatı

    @NotNull(message = "KDV oranı boş olamaz.")
    @DecimalMin(value = "0", message = "KDV oranı 0 veya daha büyük olmalıdır.")
    private BigDecimal vatRate;  // KDV oranı

    @NotBlank(message = "Tedarikçi boş olamaz.")
    private String supplier;  // Tedarikçi
}
