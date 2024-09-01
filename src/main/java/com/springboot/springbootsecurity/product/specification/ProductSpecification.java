package com.springboot.springbootsecurity.product.specification;

import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<ProductEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> name != null && !name.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<ProductEntity> hasBrand(String brand) {
        return (root, query, criteriaBuilder) -> brand != null && !brand.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + brand.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<ProductEntity> hasCode(String code) {
        return (root, query, criteriaBuilder) -> code != null && !code.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), "%" + code.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<ProductEntity> hasBarcode(String barcode) {
        return (root, query, criteriaBuilder) -> barcode != null && !barcode.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("barcode")), "%" + barcode.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<ProductEntity> hasSupplier(String supplier) {
        return (root, query, criteriaBuilder) -> supplier != null && !supplier.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("supplier")), "%" + supplier.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

}
