package com.springboot.springbootsecurity.subProduct.specification;

import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class SubProductSpecification {


    public static Specification<SubProductEntity> hasProductId(String productId) {
        return (root, query, criteriaBuilder) -> productId != null && !productId.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("productId")), "%" + productId.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<SubProductEntity> hasBarcode(String barcode) {
        return (root, query, criteriaBuilder) -> barcode != null && !barcode.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("barcode")), "%" + barcode.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<SubProductEntity> hasSupplier(String supplier) {
        return (root, query, criteriaBuilder) -> supplier != null && !supplier.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("supplier")), "%" + supplier.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

}
