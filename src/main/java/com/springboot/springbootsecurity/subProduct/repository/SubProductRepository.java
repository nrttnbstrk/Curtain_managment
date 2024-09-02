package com.springboot.springbootsecurity.subProduct.repository;

import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


    public interface SubProductRepository extends JpaRepository<SubProductEntity, String>, JpaSpecificationExecutor<SubProductEntity> {
    boolean existsProductEntityByBarcode(final String barcode);
        boolean existsProductEntityByBarcodeAndIdNot(String barcode, String id);
}
