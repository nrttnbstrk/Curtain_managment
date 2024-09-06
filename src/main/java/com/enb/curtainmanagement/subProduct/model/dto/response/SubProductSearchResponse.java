package com.enb.curtainmanagement.subProduct.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubProductSearchResponse {
    private String id;
    private String productId;
    private String barcode;
    private String supplier;
}