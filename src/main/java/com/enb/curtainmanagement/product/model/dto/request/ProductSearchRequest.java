package com.enb.curtainmanagement.product.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchRequest {
    private String name;
    private String brand;
    private String code;
    private String barcode;
    private String supplier;
}
