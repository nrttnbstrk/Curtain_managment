package com.springboot.springbootsecurity.subProduct.repository;

import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface SubProductRepository extends JpaRepository<SubProductEntity, String>, JpaSpecificationExecutor<SubProductEntity> {
    boolean existsProductEntityByBarcode(final String barcode);
        List<SubProductEntity> findByProductId(String productId);
        boolean existsProductEntityByBarcodeAndIdNot(String barcode, String id);
}
