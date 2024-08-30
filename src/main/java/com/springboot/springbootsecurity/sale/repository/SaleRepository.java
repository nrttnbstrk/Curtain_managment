package com.springboot.springbootsecurity.sale.repository;

import com.springboot.springbootsecurity.sale.model.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<SaleEntity, String> {

    List<SaleEntity> findByCustomerId(String customerId);


    boolean existsSaleEntityById(final String id);


}
