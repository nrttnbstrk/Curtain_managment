package com.enb.curtainmanagement.product.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchResponse {
    private String name;
    private String brand;
    private String code;
    private String barcode;
    private String supplier;
}
