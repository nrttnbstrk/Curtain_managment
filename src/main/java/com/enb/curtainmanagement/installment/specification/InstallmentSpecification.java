package com.enb.curtainmanagement.installment.specification;

import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentSpecification {

    public static Specification<InstallmentEntity> hasCreatedDate(LocalDate createdDate) {
        return (root, query, criteriaBuilder) -> createdDate != null ?
                criteriaBuilder.equal(root.get("createdDate"), createdDate) :
                criteriaBuilder.conjunction();
    }

    public static Specification<InstallmentEntity> hasCustomerId(String customerId) {
        return (root, query, criteriaBuilder) -> customerId != null && !customerId.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("customerId")), "%" + customerId.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<InstallmentEntity> hasInstallmentDate(LocalDate installmentDate) {
        return (root, query, criteriaBuilder) -> installmentDate != null ?
                criteriaBuilder.equal(root.get("installmentDate"), installmentDate) :
                criteriaBuilder.conjunction();
    }

    public static Specification<InstallmentEntity> hasInstallmentPrice(BigDecimal installmentPrice) {
        return (root, query, criteriaBuilder) -> installmentPrice != null ?
                criteriaBuilder.equal(root.get("installmentPrice"), installmentPrice) :
                criteriaBuilder.conjunction();
    }

    public static Specification<InstallmentEntity> hasInstallmentWhich(String installmentWhich) {
        return (root, query, criteriaBuilder) -> installmentWhich != null && !installmentWhich.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("installmentWhich")), "%" + installmentWhich.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<InstallmentEntity> hasSaleId(String saleId) {
        return (root, query, criteriaBuilder) -> saleId != null && !saleId.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("saleId")), "%" + saleId.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<InstallmentEntity> hasStatus(String status) {
        return (root, query, criteriaBuilder) -> status != null && !status.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("status")), "%" + status.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<InstallmentEntity> hasTotalPrice(BigDecimal totalPrice) {
        return (root, query, criteriaBuilder) -> totalPrice != null ?
                criteriaBuilder.equal(root.get("totalPrice"), totalPrice) :
                criteriaBuilder.conjunction();
    }

    public static Specification<InstallmentEntity> hasInstallmentQuantity(int installmentQuantity) {
        return (root, query, criteriaBuilder) -> installmentQuantity > 0 ?
                criteriaBuilder.equal(root.get("installmentQuantity"), installmentQuantity) :
                criteriaBuilder.conjunction();
    }
}
