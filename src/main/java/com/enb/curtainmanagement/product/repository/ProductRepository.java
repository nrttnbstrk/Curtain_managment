package com.enb.curtainmanagement.product.repository;

import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


    public interface ProductRepository extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {
        boolean existsProductEntityByName(final String name);
        boolean existsProductEntityByBarcode(String barcode);
        boolean existsProductEntityByCode(String code);


    }
