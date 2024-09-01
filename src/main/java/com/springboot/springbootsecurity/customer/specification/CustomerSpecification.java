package com.springboot.springbootsecurity.customer.specification;

import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
    public static Specification<CustomerEntity> hasFirstname(String firstname) {
        return (root, query, criteriaBuilder) -> firstname != null && !firstname.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstname")), "%" + firstname.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<CustomerEntity> hasLastname(String lastname) {
        return (root, query, criteriaBuilder) -> lastname != null && !lastname.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastname")), "%" + lastname.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<CustomerEntity> hasId(String id) {
        return (root, query, criteriaBuilder) -> id != null && !id.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("id")), "%" + id.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<CustomerEntity> hasPhone(String phone) {
        return (root, query, criteriaBuilder) -> phone != null && !phone.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + phone.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<CustomerEntity> hasAddress(String address) {
        return (root, query, criteriaBuilder) -> address != null && !address.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }

    public static Specification<CustomerEntity> hasLast_id(String last_id) {
        return (root, query, criteriaBuilder) -> last_id != null && !last_id.isEmpty() ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("last_id")), "%" + last_id.toLowerCase() + "%") :
                criteriaBuilder.conjunction();
    }
}
