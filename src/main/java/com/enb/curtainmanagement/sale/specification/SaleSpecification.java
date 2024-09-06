package com.enb.curtainmanagement.sale.specification;

import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import org.springframework.data.jpa.domain.Specification;

public class SaleSpecification {
    public static Specification<SaleEntity> hasCustomerId(String customerId) {
        return (root, query, criteriaBuilder) -> customerId != null && !customerId.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("customerId")), "%" + customerId.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<SaleEntity> hasProductId(String productId) {
        return (root, query, criteriaBuilder) -> productId != null && !productId.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("productId")), "%" + productId.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<SaleEntity> hasSubProductId(String subProductId) {
        return (root, query, criteriaBuilder) -> subProductId != null && !subProductId.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("subProductId")), "%" + subProductId.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }
    public static Specification<SaleEntity> hasAmount(String amount) {
        return (root, query, criteriaBuilder) -> amount != null && !amount.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("amount")), "%" + amount.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }
    public static Specification<SaleEntity> hasStatus(String status) {
        return (root, query, criteriaBuilder) -> status != null && !status.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("status")), "%" + status.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<SaleEntity> hasInstallment(String installment) {
        return (root, query, criteriaBuilder) -> installment != null && !installment.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("installment")), "%" + installment.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

}
